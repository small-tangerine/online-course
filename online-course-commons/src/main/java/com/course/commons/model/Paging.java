package com.course.commons.model;

import com.baomidou.mybatisplus.core.metadata.IPage;
import com.baomidou.mybatisplus.core.metadata.OrderItem;
import com.fasterxml.jackson.annotation.JsonIgnore;
import lombok.Getter;
import lombok.Setter;

import java.util.*;

/**
 * @date 2019-12-19
 * @Description:
 */
public class Paging<T> implements IPage<T> {
    @Getter
    private long totalCount = 0L;
    @Getter
    private long totalPage;
    @Getter
    private long prePage;
    @Getter
    private long nextPage;
    @Getter
    @Setter
    private long pageSize = 20;
    /**
     * 当前页码
     */
    @Getter
    private long page = 1;
    @Setter
    @Getter
    private List<T> items = Collections.emptyList();

    @Setter
    @Getter
    private Map<String, Object> extra;
    /**
     * 排序字段信息
     */
    @JsonIgnore
    private final List<OrderItem> orders = new ArrayList<>();

    /**
     * 是否进行sql优化
     */
    @Setter
    @Getter
    @JsonIgnore
    private Boolean isOptimizeCountSql;
    /**
     * 是否搜索
     */
    @Setter
    @JsonIgnore
    private boolean notSearchCount;

    @Override
    public boolean optimizeCountSql() {
        if (Objects.isNull(isOptimizeCountSql)) {
            return true;
        }
        return isOptimizeCountSql;
    }

    public Paging(Long currentPage, Long pageSize) {
        if (Objects.nonNull(currentPage) && currentPage > 1) {
            this.page = currentPage;
        }
        if (Objects.nonNull(pageSize) && pageSize > 0) {
            this.pageSize = pageSize;
        }
    }

    public Paging(Integer currentPage, Integer pageSize) {
        if (Objects.nonNull(currentPage) && currentPage > 1) {
            this.page = currentPage;
        }
        if (Objects.nonNull(pageSize) && pageSize > 0) {
            this.pageSize = pageSize;
        }
    }

    @Override
    public List<OrderItem> orders() {
        return this.orders;
    }

    @JsonIgnore
    @Override
    public List<T> getRecords() {
        return this.items;
    }

    @JsonIgnore
    @Override
    public IPage<T> setRecords(List<T> records) {
        this.items = records;
        return this;
    }

    public boolean isFirstPage() {
        return page == 1;
    }

    /**
     * 是否是最后一页，小程序用
     *
     * @return true or false
     */
    public boolean isLastPage() {
        return page >= getPages();
    }

    /**
     * 是否真正的最后一页
     *
     * @return true or false
     */
    @JsonIgnore
    public boolean isRealLastPage() {
        if (Objects.equals(0L, getPages())) {
            return true;
        }
        return page >= getPages();
    }

    @JsonIgnore
    @Override
    public long getTotal() {
        return getTotalCount();
    }

    @Override
    public IPage<T> setTotal(long total) {
        setTotalCount(total);
        return this;
    }

    @JsonIgnore
    @Override
    public long getSize() {
        return this.pageSize;
    }

    @Override
    public IPage<T> setSize(long size) {
        this.pageSize = size;
        return this;
    }

    @JsonIgnore
    @Override
    public long getCurrent() {
        return page;
    }

    @Override
    public IPage<T> setCurrent(long current) {
        setPage(current);
        return this;
    }

    @Override
    @JsonIgnore
    public boolean searchCount() {
        return !notSearchCount;
    }

    public void setPage(long page) {
        if (page >= 1) {
            this.page = page;
            this.prePage = page - 1;
            this.nextPage = page + 1;
        }
    }

    public void setTotalCount(long totalCount) {
        this.totalCount = totalCount;
        this.totalPage = getPages();
    }
}
