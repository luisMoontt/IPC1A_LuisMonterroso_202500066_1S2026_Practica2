package com.ipc1_practica2.models;
/**
 * @author Admin
 */
public class Serie {
    private int[] numbers = new int[100];
    private int algoritm;
    private boolean order;
    private int speed;
    private int comparison;
    private int exchange;
    private int iteration;

    public Serie() {
    }

    public Serie(int algoritm, boolean order, int speed, int comparison, int exchange, int iteration) {
        this.algoritm = algoritm;
        this.order = order;
        this.speed = speed;
        this.comparison = comparison;
        this.exchange = exchange;
        this.iteration = iteration;
    }

    public int[] getNumbers() {
        return numbers;
    }

    public void setNumbers(int[] numbers) {
        this.numbers = numbers;
    }

    public int getAlgoritm() {
        return algoritm;
    }

    public void setAlgoritm(int algoritm) {
        this.algoritm = algoritm;
    }

    public boolean isOrder() {
        return order;
    }

    public void setOrder(boolean order) {
        this.order = order;
    }

    public int getSpeed() {
        return speed;
    }

    public void setSpeed(int speed) {
        this.speed = speed;
    }

    public int getComparison() {
        return comparison;
    }

    public void setComparison(int comparison) {
        this.comparison = comparison;
    }

    public int getExchange() {
        return exchange;
    }

    public void setExchange(int exchange) {
        this.exchange = exchange;
    }

    public int getIteration() {
        return iteration;
    }

    public void setIteration(int iteration) {
        this.iteration = iteration;
    }
}
