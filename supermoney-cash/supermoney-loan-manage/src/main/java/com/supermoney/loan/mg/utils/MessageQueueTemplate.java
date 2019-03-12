package com.supermoney.loan.mg.utils;


import java.util.List;

public interface MessageQueueTemplate<E> {

    /**
     * 用于批量处理的数据
     * @return
     */
    public List<E> baseData();

    /**
     * 数据的具体操作
     * @param object
     */
    public void consumer(E object);

    /**
     *停止条件
     * @param baseData 基础数据
     * @param i        当前执行第几条数据
     */
    public Boolean stop(List<E> baseData, int i);
}
