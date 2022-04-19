package com.cr6588.vo;

import com.baomidou.mybatisplus.extension.plugins.pagination.Page;

import lombok.AllArgsConstructor;
import lombok.Data;
import lombok.NoArgsConstructor;

/**
 * create in 2022年04月03日
 * @see Page
 * @category TODO
 * @author chenyi
 */
@Data
@NoArgsConstructor
@AllArgsConstructor
public class Pager {
    /**
     * 总数
     */
    protected long total = 0;
    /**
     * 每页显示条数，默认 10
     */
    protected long size = 10;

    /**
     * 当前页
     */
    protected long current = 1;

    protected boolean searchCount = true;

    public Pager(long current, long size) {
        this(0, size, current, true);
    }

    public Pager(long size, long current, boolean searchCount) {
        this.size = size;
        this.current = current;
        this.searchCount = searchCount;
    }

    public Pager(long total, long size, long current) {
        this.total = total;
        this.size = size;
        this.current = current;
    }
}
