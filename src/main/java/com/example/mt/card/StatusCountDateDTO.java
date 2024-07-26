package com.example.mt.card;

import java.time.LocalDate;

public class StatusCountDateDTO {
    Integer clientId;
    LocalDate date;
    private Long visitedCount;
    private Long downloadedCount;

    public StatusCountDateDTO(Integer clientId, LocalDate date, Long visitedCount, Long downloadedCount) {
        this.clientId = clientId;
        this.date = date;
        this.visitedCount = visitedCount;
        this.downloadedCount = downloadedCount;
    }

    public StatusCountDateDTO() {
    }

    public Integer getClientId() {
        return clientId;
    }

    public void setClientId(Integer clientId) {
        this.clientId = clientId;
    }

    public LocalDate getDate() {
        return date;
    }

    public void setDate(LocalDate date) {
        this.date = date;
    }

    public Long getVisitedCount() {
        return visitedCount;
    }

    public void setVisitedCount(Long visitedCount) {
        this.visitedCount = visitedCount;
    }

    public Long getDownloadedCount() {
        return downloadedCount;
    }

    public void setDownloadedCount(Long downloadedCount) {
        this.downloadedCount = downloadedCount;
    }
}
