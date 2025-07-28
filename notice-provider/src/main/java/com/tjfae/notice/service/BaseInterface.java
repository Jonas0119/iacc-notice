// 
// Decompiled by Procyon v0.6.0
// 

package com.tjfae.notice.service;

import java.util.List;

public interface BaseInterface<T>
{
    int deleteById(final Long p0);
    
    T findById(final Long p0);
    
    void saveAll(final List<T> p0);
    
    void save(final T p0);
    
    int insert(final T p0);
    
    int update(final T p0);
    
    int insertBatch(final List<T> p0);
    
    int updateBatch(final List<T> p0);
}
