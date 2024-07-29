package dev.sergioescriva.wallet.services;

import java.util.List;

import org.springframework.web.bind.annotation.RequestBody;

import dev.sergioescriva.wallet.models.Transaction;
import dev.sergioescriva.wallet.models.User;

public interface TransactionService {

    /*
     * class transactionAddDic(BaseModel):id:Optional[str]=
     * None
     * category:
     * str
     * description:
     * str
     * amount:
     * float user_id:
     * str
     * date:Optional[str]=
     * None
     * wallet_id:
     * int participants:
     * list
     */// transactions

    List<Transaction> getAllTransactionByWalletId(Long walletId);

    Transaction getTransactionById(Long transactionId);

    Double getBalanceById(Long walletId);

    User getBalanceMinById(Long walletId);

    void addTransaction(@RequestBody Transaction transaction);

    void updateTransaction(@RequestBody Transaction transaction);

    void delTransactionById(Long delId);
}
