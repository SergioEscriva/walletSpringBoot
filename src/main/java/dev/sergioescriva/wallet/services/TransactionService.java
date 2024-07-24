package dev.sergioescriva.wallet.services;

import org.springframework.web.bind.annotation.RequestBody;

import dev.sergioescriva.wallet.models.Transaction;

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

    void getAllTransactionByWalletId(Long walletId);

    void getTransactionById(Long transactionId);

    void getBalanceById(Long walletId);

    void getBalanceMinById(Long walletId);

    void addTransaction(@RequestBody Transaction transaction);

    void updateTransaction(@RequestBody Transaction transaction);

    void delTransactionById(Long delId);
}
