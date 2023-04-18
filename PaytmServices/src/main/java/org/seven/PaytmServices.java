package org.seven;

import java.util.List;

import javax.ejb.Local;
import javax.ejb.Remote;

@Remote
@Local
public interface PaytmServices {
   List<PaytmTransaction> loadAllTransaction(int offset,int limit,String orderField);
   
}
