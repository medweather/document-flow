package ru.medweather.documentflow.models;

import javax.persistence.*;
import javax.validation.constraints.NotNull;

@Entity
@Table(name = "restriction")
public class Restriction {

    @Id
    @GeneratedValue(strategy = GenerationType.AUTO)
    @NotNull
    private int id;
    @Column(name = "time_start")
    private int timeStart;
    @Column(name = "time_finish")
    private int timeFinish;
    @Column(name = "count_doc_flow")
    private int countDocFlow;
    @Column(name = "count_create_doc")
    private int countCreateDoc;
    @Column(name = "period_create_doc")
    private int periodCreateDoc;
    @Column(name = "count_flow_bet_both_sides")
    private int countFlowBetBothSides;

    public Restriction() {
    }

    public Restriction(int timeStart, int timeFinish, int countDocFlow, int countCreateDoc, int periodCreateDoc, int countFlowBetBothSides) {
        this.timeStart = timeStart;
        this.timeFinish = timeFinish;
        this.countDocFlow = countDocFlow;
        this.countCreateDoc = countCreateDoc;
        this.periodCreateDoc = periodCreateDoc;
        this.countFlowBetBothSides = countFlowBetBothSides;
    }

    public int getId() {
        return id;
    }

    public void setId(int id) {
        this.id = id;
    }

    public int getTimeStart() {
        return timeStart;
    }

    public void setTimeStart(int timeStart) {
        this.timeStart = timeStart;
    }

    public int getTimeFinish() {
        return timeFinish;
    }

    public void setTimeFinish(int timeFinish) {
        this.timeFinish = timeFinish;
    }

    public int getCountDocFlow() {
        return countDocFlow;
    }

    public void setCountDocFlow(int countDocFlow) {
        this.countDocFlow = countDocFlow;
    }

    public int getCountCreateDoc() {
        return countCreateDoc;
    }

    public void setCountCreateDoc(int countCreateDoc) {
        this.countCreateDoc = countCreateDoc;
    }

    public int getCountFlowBetBothSides() {
        return countFlowBetBothSides;
    }

    public void setCountFlowBetBothSides(int countFlowBetBothSides) {
        this.countFlowBetBothSides = countFlowBetBothSides;
    }

    public int getPeriodCreateDoc() {
        return periodCreateDoc;
    }

    public void setPeriodCreateDoc(int periodCreateDoc) {
        this.periodCreateDoc = periodCreateDoc;
    }
}
