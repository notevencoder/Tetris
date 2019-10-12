package com.mygdx.game;

import com.badlogic.gdx.Gdx;
import com.badlogic.gdx.Input;

public class Figure {
    //Внутренний класс для хранения координат фигуры и координат точек для проверки коллизий
    private class Coordinates {
        //В массивах чётное число элементов. Каждая пара элементов (x,y) - координаты точки от-но центра фигуры
        int[] Coordinates;  //координаты фигуры
        int[] CheckLeft;    //координаты точек слева, справа, снизу и сверху от фигуры для проверки возможности движения в сторону и падания
        int[] CheckUp;      //координаты точек над фигурой нужны для того, чтобы рассчитывать другие координаты при вращении в ф-ции rotate
        int[] CheckRight;
        int[] CheckBottom;

        //в конструктор записаны координаты фигур в зависимости от типа
        Coordinates(int FigureType) {
            switch (FigureType) {
                case 0:
                    Coordinates = new int[]{-1, 0, -1, 1, 0, 1};
                    CheckLeft = new int[]{-2, 0, -2, 1};
                    CheckUp = new int[]{-1, -1, 0, -1};
                    CheckRight = new int[]{1, 0, 1, 1};
                    CheckBottom = new int[]{-1, 2, 0, 2};
                    break;
                case 1:
                    Coordinates = new int[]{-2, 0, -1, 0, 1, 0};
                    CheckLeft = new int[]{-3, 0};
                    CheckUp = new int[]{-2, -1, -1, -1, 0, -1, 1, -1};
                    CheckRight = new int[]{2, 0};
                    CheckBottom = new int[]{-2, 1, -1, 1, 0, 1, 1, 1};
                    break;
                case 2:
                    Coordinates = new int[]{-1, 1, 0, 1, 1, 0};
                    CheckLeft = new int[]{-2, 1, -1, 0};
                    CheckUp = new int[]{-1, 0, 0, -1, 1, -1};
                    CheckRight = new int[]{2, 0, 1, 1};
                    CheckBottom = new int[]{-1, 2, 0, 2, 1, 1};
                    break;
                case 3:
                    Coordinates = new int[]{-1, 0, 0, 1, 1, 1};
                    CheckLeft = new int[]{-1, 1, -2, 0};
                    CheckUp = new int[]{-1, -1, 0, -1, 1, 0};
                    CheckRight = new int[]{1, 0, 2, 1};
                    CheckBottom = new int[]{-1, 1, 0, 2, 1, 2};
                    break;
                case 4:
                    Coordinates = new int[]{-1, 0, 0, 1, 1, 0};
                    CheckLeft = new int[]{-2, 0, -1, 1};
                    CheckUp = new int[]{-1, -1, 0, -1, 1, -1};
                    CheckRight = new int[]{2, 0, 1, 1};
                    CheckBottom = new int[]{-1, 1, 0, 2, 1, 1};
                    break;
                case 5:
                    Coordinates = new int[]{-1, 0, 1, 0, 1, 1};
                    CheckLeft = new int[]{-2, 0, 0, 1};
                    CheckUp = new int[]{-1, -1, 0, -1, 1, -1};
                    CheckRight = new int[]{2, 0, 2, 1};
                    CheckBottom = new int[]{0, 1, -1, 1, 1, 2};
                    break;
                case 6:
                    Coordinates = new int[]{-1, 0, 1, 0, -1, 1};
                    CheckLeft = new int[]{-2, 0, -2, 1};
                    CheckUp = new int[]{-1, -1, 0, -1, 1, -1};
                    CheckRight = new int[]{2, 0, 0, 1};
                    CheckBottom = new int[]{0, 1, 1, 1, -1, 2};
                    break;
            }
        }
    }

    public boolean isLanded = false;   //Переменная для проверки приземления. Если она равна true, создаётся новая фигура, а текущий экземпляр фигуры уничтожается (ячейки фигуры на поле остаются как статичные объекты)
    //0 - квадрат
    //1 - палка
    //2,3 - фигуры S и Z
    //4 - фигура T
    //5,6 - фигуры J и L
    int type;                   //Тип фигуры. Число от 0 до 6.
    private boolean state = false;      //Перемення для фигур I,S и Z, необходимая для их корректного вращения, т.к. оно отличается от вращения остальных фигур
    private int maxstate;               //Переменная, в которой хранится максимальное число положений фигуры при вращении
    private Coordinates coords; //Переменная для хранения координат (см. класс Coordinates)
    private int color;                  //Переменная для хранения цвета фигуры. Число от 1 до 5.
    private int x, y;                   //Переменные для хранения координат центральной ячейки фигуры на поле
    private int[][] mas;                //Массив игрового поля

    //Конструктов класса
    Figure(int type, int color, int SpawnX, int SpawnY, int[][] mas) {
        this.type = type;
        coords = new Coordinates(type);
        //maxstate зависит от типа фигуры
        switch (type) {
            case 0:
                this.maxstate = 1;
                break;
            case 1:
                this.maxstate = 2;
                break;
            case 2:
                this.maxstate = 2;
                break;
            case 3:
                this.maxstate = 2;
                break;
            case 4:
                this.maxstate = 4;
                break;
            case 5:
                this.maxstate = 4;
                break;
            case 6:
                this.maxstate = 4;
                break;
        }
        this.color = color;
        this.mas = mas;
        this.x = SpawnX;
        this.y = SpawnY;
        draw();
    }

    //Функция для проверки коллизий. Получает на вход массив относительных координат.
    //Возвращает false, если хотя бы одна из этих точек на поле не равна нулю или находится за пределами поля. Иначе возвращает true
    boolean checkCollisions(int[] coordsXY) {
        int checkX, checkY;
        for (int i = 0; i < coordsXY.length - 1; i += 2) {
            checkX = x + coordsXY[i];
            checkY = y + coordsXY[i + 1];
            if (checkX < 0 || checkX >= GameLogic.width || checkY < 0 || checkY >= GameLogic.height || mas[checkY][checkX] != 0)
                return false;
        }
        return true;
    }

    //Функция, добавляющая фигуру в массив игрового поля
    void draw() {
        mas[y][x] = color;
        mas[y + coords.Coordinates[1]][x + coords.Coordinates[0]] = color;
        mas[y + coords.Coordinates[3]][x + coords.Coordinates[2]] = color;
        mas[y + coords.Coordinates[5]][x + coords.Coordinates[4]] = color;
    }

    //Функция, удаляющая фигуру из массивф игрового поля
    void clear() {
        mas[y][x] = 0;
        mas[y + coords.Coordinates[1]][x + coords.Coordinates[0]] = 0;
        mas[y + coords.Coordinates[3]][x + coords.Coordinates[2]] = 0;
        mas[y + coords.Coordinates[5]][x + coords.Coordinates[4]] = 0;
    }

    //Функция, сдвигающая фигуру на одну клетку вниз, если это возножно
    void fall() {
        if (checkCollisions(coords.CheckBottom)) {
            clear();
            y++;
            draw();
        } else {
            isLanded = true;
            if (y == 0) GameLogic.GameOver();
        }
    }

    //Функция, осуществляющая движение фигуры по горизонтали и вращение при нажатии соот. кнопок
    void move() {
        if (Gdx.input.isKeyJustPressed(Input.Keys.LEFT)) {
            if (checkCollisions(coords.CheckLeft)) {
                clear();
                x--;
                draw();
                return;
            }
        }
        if (Gdx.input.isKeyJustPressed(Input.Keys.RIGHT)) {
            if (checkCollisions(coords.CheckRight)) {
                clear();
                x++;
                draw();
                return;
            }
        }
        if (Gdx.input.isKeyJustPressed(Input.Keys.UP)) {
            clear();
            setState(true);
            draw();
            return;
        }
        if (Gdx.input.isKeyJustPressed(Input.Keys.DOWN)) {
            clear();
            setState(false);
            draw();
        }
    }

    //Функции вращения фигуры. Rotate вращает ей по часовой клетке, rotateBack - против часовой
    void rotate() {
        int[] temp;
        temp = flip(coords.Coordinates);
        if (checkCollisions(temp)) {
            coords.Coordinates = temp.clone();
            temp = coords.CheckLeft.clone();
            coords.CheckLeft = flip(coords.CheckBottom);
            coords.CheckBottom = flip(coords.CheckRight);
            coords.CheckRight = flip(coords.CheckUp);
            coords.CheckUp = flip(temp);
        }
    }

    void rotateBack() {
        int[] temp;
        temp = flipBack(coords.Coordinates);
        if (checkCollisions(temp)) {
            coords.Coordinates = temp.clone();
            temp = coords.CheckLeft.clone();
            coords.CheckLeft = flipBack(coords.CheckUp);
            coords.CheckUp = flipBack(coords.CheckRight);
            coords.CheckRight = flipBack(coords.CheckBottom);
            coords.CheckBottom = flipBack(temp);
        }
    }

    //Вспомогательные функциидля вращения, рассчитываюющие новые координаты фигуры, исходя из старых и формулы поворота точек от-но числовой оси на 90 градусов
    int[] flip(int[] mas2) {
        int[] res = new int[mas2.length];
        for (int i = 0; i < mas2.length; i += 2) {
            res[i] = -mas2[i + 1];
            res[i + 1] = mas2[i];
        }
        return res;
    }

    int[] flipBack(int[] mas2) {
        int[] res = new int[mas2.length];
        for (int i = 0; i < mas2.length; i += 2) {
            res[i] = mas2[i + 1];
            res[i + 1] = -mas2[i];
        }
        return res;
    }

    //Функция, определающая, в какую сторону необходимо повернуть фигуру в зависимости от её типа и положения
    void setState(boolean isClockwise) {
        switch (maxstate) {
            case 2:
                if (state) rotate();
                else rotateBack();
                state = !state;
                break;
            case 4:
                if (isClockwise) rotate();
                else rotateBack();
                break;
        }
    }
}