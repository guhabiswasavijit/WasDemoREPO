package org.seven;

import java.io.BufferedReader;
import java.io.IOException;
import java.io.PrintWriter;
import java.text.SimpleDateFormat;
import java.util.List;

import javax.ejb.EJB;
import javax.servlet.ServletConfig;
import javax.servlet.ServletException;
import javax.servlet.http.HttpServlet;
import javax.servlet.http.HttpServletRequest;
import javax.servlet.http.HttpServletResponse;

import org.json.JSONArray;
import org.json.JSONObject;


public class PaytmServlet extends HttpServlet {
	
    private static final long serialVersionUID = 1L;
    @EJB 
    private PaytmServices paytmServices;
    private static ThreadLocal<SimpleDateFormat> DATE_FORMAT = new ThreadLocal<SimpleDateFormat>() {
        @Override
        protected SimpleDateFormat initialValue() {
            return new SimpleDateFormat("yyyy-MM-dd");
        }
    };
	public void init(ServletConfig config) throws ServletException {
		System.out.println("PaytmServlet \"Init\" method called");
	}
	public void destroy() {
		System.out.println("PaytmServlet \"Destroy\" method called");
	}
	protected void doGet(HttpServletRequest request, HttpServletResponse response) throws ServletException, IOException {
		PrintWriter out = response.getWriter();
		StringBuilder buffer = new StringBuilder();
	    BufferedReader reader = request.getReader();
	    String line;
	    while ((line = reader.readLine()) != null) {
	        buffer.append(line);
	        buffer.append(System.lineSeparator());
	    }
	    String requestData = buffer.toString();
	    JSONObject requestJson = (JSONObject) JSONObject.stringToValue(requestData);
        int page = Integer.parseInt((String)requestJson.get("pageNumber"));
        int limit = Integer.parseInt((String)requestJson.get("limit"));
        String sortField = (String)requestJson.get("sort");
		List<PaytmTransaction> trans = paytmServices.loadAllTransaction(page,limit,sortField);
		JSONArray payTrns = new JSONArray();
		trans.forEach(payTx -> {
			JSONObject payTrn = new JSONObject();
			payTrns.put(payTrn);
			payTrn.put("txDate", DATE_FORMAT.get().format(payTx.getTransactionDate()));
			payTrn.put("activity",payTx.getActivity() );
			payTrn.put("sourceDestination",payTx.getSourceDestination());
			payTrn.put("walletTxId", payTx.getWalletTxId());
			payTrn.put("usr_comment",payTx.getUsrComment());
			payTrn.put("debit",payTx.getDebit());
			payTrn.put("credit",payTx.getCredit());
			payTrn.put("transaction_breakup",payTx.getTransactionBreakUp());
			payTrn.put("status",payTx.getTrasactionStatus());
		});
		out.write(payTrns.toString());
	}

	protected void doPost(HttpServletRequest request, HttpServletResponse response) throws ServletException, IOException {
       doGet(request,response);
	}

}
