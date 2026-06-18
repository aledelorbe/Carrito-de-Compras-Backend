package com.alejandro.carritodecompras.product.models.dtos;


import java.util.List;

import org.springframework.data.domain.Page;


public class PageResponseDto<T> {

    private List<T> data;

    private int currentPage;

    private int pageSize;

    private long totalElements;

    private int totalPages;

    private boolean hasNext;

    private boolean hasPrevious;

    public List<T> getData() {
        return data;
    }

    public void setData(List<T> data) {
        this.data = data;
    }

    public int getCurrentPage() {
        return currentPage;
    }

    public void setCurrentPage(int currentPage) {
        this.currentPage = currentPage;
    }

    public int getPageSize() {
        return pageSize;
    }

    public void setPageSize(int pageSize) {
        this.pageSize = pageSize;
    }

    public long getTotalElements() {
        return totalElements;
    }

    public void setTotalElements(long totalElements) {
        this.totalElements = totalElements;
    }

    public int getTotalPages() {
        return totalPages;
    }

    public void setTotalPages(int totalPages) {
        this.totalPages = totalPages;
    }

    public boolean isHasNext() {
        return hasNext;
    }

    public void setHasNext(boolean hasNext) {
        this.hasNext = hasNext;
    }

    public boolean isHasPrevious() {
        return hasPrevious;
    }

    public void setHasPrevious(boolean hasPrevious) {
        this.hasPrevious = hasPrevious;
    }

    public static <T> PageResponseDto<T> fromPage(Page<T> page) {
        PageResponseDto<T> dto = new PageResponseDto<>();

        dto.setData(page.getContent());
        dto.setCurrentPage(page.getNumber());
        dto.setPageSize(page.getSize());
        dto.setTotalElements(page.getTotalElements());
        dto.setTotalPages(page.getTotalPages());
        dto.setHasNext(page.hasNext());
        dto.setHasPrevious(page.hasPrevious());
        
        return dto;
    }

}

