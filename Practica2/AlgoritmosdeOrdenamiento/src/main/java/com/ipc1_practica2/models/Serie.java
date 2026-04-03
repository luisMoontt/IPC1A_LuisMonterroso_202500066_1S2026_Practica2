package com.ipc1_practica2.models;
/**
 * @author Admin
 */
public class Serie {
    private int[] numbers = new int[100];
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
    
    
    
    

}
