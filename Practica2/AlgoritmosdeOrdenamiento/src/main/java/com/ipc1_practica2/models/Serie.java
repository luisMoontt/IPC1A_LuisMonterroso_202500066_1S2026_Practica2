package com.ipc1_practica2.models;
/**
 * @author Admin
 */
public class Serie {
    private int[] numbers;
    private int t_algoritm;
    private boolean t_order;
    private int speed;
    private int n_comparison;
    private int n_exchange;
    private int n_iteration;

    public Serie() {
    }

    public Serie(int[] numbers, int t_algoritm, boolean t_order, int speed, int n_comparison, int n_exchange, int n_iteration) {
        this.numbers = numbers;
        this.t_algoritm = t_algoritm;
        this.t_order = t_order;
        this.speed = speed;
        this.n_comparison = n_comparison;
        this.n_exchange = n_exchange;
        this.n_iteration = n_iteration;
    }

    public int[] getNumbers() {
        return numbers;
    }

    public void setNumbers(int[] numbers) {
        this.numbers = numbers;
    }

    public int getT_algoritm() {
        return t_algoritm;
    }

    public void setT_algoritm(int t_algoritm) {
        this.t_algoritm = t_algoritm;
    }

    public boolean isT_order() {
        return t_order;
    }

    public void setT_order(boolean t_order) {
        this.t_order = t_order;
    }

    public int getSpeed() {
        return speed;
    }

    public void setSpeed(int speed) {
        this.speed = speed;
    }

    public int getN_comparison() {
        return n_comparison;
    }

    public void setN_comparison(int n_comparison) {
        this.n_comparison = n_comparison;
    }

    public int getN_exchange() {
        return n_exchange;
    }

    public void setN_exchange(int n_exchange) {
        this.n_exchange = n_exchange;
    }

    public int getN_iteration() {
        return n_iteration;
    }

    public void setN_iteration(int n_iteration) {
        this.n_iteration = n_iteration;
    }
}
