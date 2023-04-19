package org.seven;

import java.util.List;

import javax.ejb.Local;


@Local
public interface PaytmServices {
   List<PaytmTransaction> loadAllTransaction(int offset,int limit,String orderField);
   
}
