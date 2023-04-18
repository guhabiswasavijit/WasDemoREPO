package org.seven;

import java.sql.Date;

import javax.persistence.Column;
import javax.persistence.Entity;
import javax.persistence.GeneratedValue;
import javax.persistence.GenerationType;
import javax.persistence.Id;
import javax.persistence.SequenceGenerator;
import javax.persistence.Table;

import lombok.EqualsAndHashCode;
import lombok.Getter;
import lombok.Setter;
import lombok.ToString;

@Entity
@Table(name="PAYTM_TX")
@Getter
@Setter
@ToString
@EqualsAndHashCode
public class PaytmTransaction {
    @Id
	@GeneratedValue(strategy = GenerationType.SEQUENCE,generator = "id_generator")
	@SequenceGenerator(name="id_generator", sequenceName = "SEQ_GEN_IDENTITY", initialValue = 1000)
	private Long id;
    @Column(name = "tx_date")
	private Date transactionDate;
    @Column(name = "activity")
	private String activity;
    @Column(name = "sourceDestination")
	private String sourceDestination;
    @Column(name = "walletTxId")
	private String walletTxId;
    @Column(name = "usr_comment")
	private String usrComment;
    @Column(name = "debit")
	private Long debit;
    @Column(name = "credit")
	private Long credit;
    @Column(name = "transaction_breakup")
	private String transactionBreakUp;
    @Column(name = "status")
	private String trasactionStatus;

}
