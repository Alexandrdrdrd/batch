package com.example.batch.batch.models;

import javax.persistence.*;

@Entity
@Table(name = "exchange_rates")
public class ExchangeRates {
    @Id
    @Column(name = "id")
    @GeneratedValue(strategy = GenerationType.IDENTITY)
    int id;

    @Column(name = "r030")
    int r030;
    @Column(name = "txt")
    String txt;
    @Column(name = "rate")
    float rate;
    @Column(name = "cc")
    String cc;
    @Column(name = "exchangedate")
    String exchangedate;
    @Column(name = "rate_difference")
    Float rateDifference = null;

    public Float getRateDifference() {
        return rateDifference;
    }

    public void setRateDifference(Float rateDifference) {
        this.rateDifference = rateDifference;
    }

    public int getR030() {
        return r030;
    }

    public void setR030(int r030) {
        this.r030 = r030;
    }

    public String getTxt() {
        return txt;
    }

    public void setTxt(String txt) {
        this.txt = txt;
    }

    public float getRate() {
        return rate;
    }

    public void setRate(float rate) {
        this.rate = rate;
    }

    public String getCc() {
        return cc;
    }

    public void setCc(String cc) {
        this.cc = cc;
    }

    public String getExchangedate() {
        return exchangedate;
    }

    public void setExchangedate(String exchangedate) {
        this.exchangedate = exchangedate;
    }

    public int getId() {
        return id;
    }

    public void setId(int id) {
        this.id = id;
    }

    public ExchangeRates() {
    }

    public ExchangeRates(int r030, String txt, float rate, String cc, String exchangedate) {
        this.r030 = r030;
        this.txt = txt;
        this.rate = rate;
        this.cc = cc;
        this.exchangedate = exchangedate;
    }

    @Override
    public String toString() {
        return "UAHToForeign{" +
                "r030=" + r030 +
                ", txt='" + txt + '\'' +
                ", rate='" + rate + '\'' +
                ", cc='" + cc + '\'' +
                ", exchangedate='" + exchangedate + '\'' +
                '}';
    }
}
