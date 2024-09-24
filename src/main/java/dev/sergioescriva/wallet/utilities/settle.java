package dev.sergioescriva.wallet.utilities;

import java.util.List;

import org.hibernate.engine.transaction.spi.TransactionObserver;
import org.springframework.beans.factory.annotation.Autowired;

import dev.sergioescriva.wallet.models.Transaction;
import dev.sergioescriva.wallet.models.User;
import dev.sergioescriva.wallet.repositories.TransactionRepository;
import dev.sergioescriva.wallet.services.TransactionServiceImp;

public class settle {

   //@Autowired
    //TransactionRepository repository;
 

     public void walletTransactions(long walletId){
        TransactionServiceImp transactionsAll = new TransactionServiceImp();
     
        //prueba
         walletId = 1;
      List<Transaction> transactions = transactionsAll.getAllTransactionByWalletId(walletId);  //get().transactions(wallet_id);
      
      List<User> allNames = ("");
     * allNamesTransactions = [];
     * for idTransac in transactions:
     * participants = idTransac["participants"]
     * #participants = participants.split(",")
     * name = idTransac["name"]
     * amount = idTransac["amount"]
     * dats = (name,amount)
     * allNames.append(dats)
     * for names in participants:
     * name = (names,0.0)
     * allNames.append(name)
     * allNamesTransactions.append(allNames)
     * membersAmounts = {}
     * for x in allNamesTransactions[0]:
     * membersAmounts.setdefault(x[0],[]).append(x[1])
     * membersAmountsTotal = []
     * membersBalanceTotal = {}
     * for n in membersAmounts:
     * member_amount = sum(membersAmounts[n])
     * member_balance_total = {"%s" % n:member_amount}
     * membersBalance_total.update(member_balance_total)
     * #print ("walletTransactions",membersBalance_total)
     * return membersBalance_total
     * 
     * def divisionTransactions(self,wallet_id):
     * transactions = Transaction().transactions(wallet_id)
     * participants_amount = []
     * for transac_one in transactions:
     * transaction_one = Settle().namesTransaction(transac_one)
     * amounts = Settle().amountPerFriend(transaction_one)
     * totalDebt = amounts[0]
     * amountPerFriend = amounts[1]
     * transaction = transaction_one[0]
     * participants = transaction_one[1]
     * debts = []
     * 
     * for participant,amount in transaction:
     * if amount <=0:
     * debts = (participant, round(amount - amountPerFriend,2) )
     * participants_amount.append(debts)
     * if amount >0:
     * debts = (participant, totalDebt )
     * participants_amount.append(debts)
     * 
     * participants_amounts = {}
     * for x in participants_amount:
     * participants_amounts.setdefault(x[0],[]).append(x[1])
     * participants_balance_total = {}
     * 
     * for n in participants_amounts:
     * amount = participants_amounts[n]
     * participant_amount = sum(amount)
     * participant_balance_total = {"%s" % n:round(participant_amount,2)}
     * participants_balance_total.update(participant_balance_total)
     * 
     * #print ("divisionTransactions",participants_balance_total)
     * 
     * return participants_balance_total
     * 
     * def namesTransaction(self,transaction):
     * allNamesTransactions = []
     * participants = transaction["participants"]
     * name = transaction["name"]
     * amount = transaction["amount"]
     * dats = [(name,amount)]
     * for names in participants:
     * name = (names,0.0)
     * dats.append(name)
     * allNamesTransactions = dats
     * membersTotal = [allNamesTransactions, participants]
     * return membersTotal
     * 
     * def amountPerFriend(self,transaction):
     * #transaccion = list(transaccion[0].items())
     * # Calculamos la deuda total
     * totalDebt = sum(friend[1] for friend in transaction[0])
     * # Calculamos la cantidad que cada amigo debería pagar
     * participants = (transaction[1])
     * amountPerFriend = totalDebt / len(participants)
     * friendAmount = round(totalDebt,2), round(amountPerFriend,2)
     * return friendAmount
     * 
     * 
     * 
     * def divisionWallet(self,wallet_id):
     * debts = Settle().divisionTransactions(wallet_id)
     * pay = [(name, amount) for name, amount in debts.items() if amount <= 0]
     * receive = [(name, amount) for name, amount in debts.items() if amount > 0]
     * # Ordenamos la lista de los que tienen que pagar de menor a mayor cantidad
     * pay.sort(key=lambda x: x[1])
     * # Ordenamos la lista de los que tienen que recibir de mayor a menor cantidad
     * receive.sort(key=lambda x: x[1], reverse=True)
     * # Creamos un diccionario para almacenar las soluciones
     * solutions = []
     * # Iteramos sobre la lista de los que tienen que pagar
     * for payer, amountPay in pay:
     * # Iteramos sobre la lista de los que tienen que recibir
     * while abs(amountPay) > 0:
     * amountPay = round(amountPay,2)
     * for receiver, amountReceive in receive:
     * amountReceive = round(amountReceive,2)
     * # Si el receptor debe recibir más de lo que el pagador tiene que pagar
     * if amountReceive > abs(amountPay):
     * amountPayIni = amountPay
     * # El pagador paga la cantidad que debe al receptor
     * solutions.append([payer,receiver,abs(amountPay)])
     * #solutions[f"{payer} paga a {receiver}"] = abs(amountPay)
     * # Actualizamos la cantidad que el receptor tiene que recibir
     * receive[receive.index((receiver, round(amountReceive,2)))] = (receiver,
     * round(amountReceive + amountPay,2))
     * # El pagador ya no tiene que pagar nada
     * 
     * amountPay = 0.0
     * pay[pay.index((payer, amountPayIni))] = (payer, amountPay)
     * # Salimos del bucle interno
     * break
     * # Si el receptor debe recibir menos o lo mismo de lo que el pagador tiene que
     * pagar
     * if abs(amountPay) == 0:
     * # Salimos del bucle interno
     * break
     * if abs(amountPay) > 0:
     * amountPayIni = amountPay
     * # El pagador paga la cantidad que el receptor tiene que recibir
     * solutions.append([payer,receiver,abs(amountReceive)])
     * #solutions[f"{payer} paga a {receiver}"] = amountReceive
     * # Actualizamos la cantidad que el pagador tiene que pagar
     * amountPay += amountReceive
     * # El receptor ya no tiene que recibir nada
     * receive.remove((receiver, round(amountReceive,2)))
     * pay[pay.index((payer, amountPayIni))] = (payer, round(amountPay,2))
     * break
     * if len(receive) == 0:
     * print ("Deduda sin Saldar--- pagador",pay,"recibe",receive)
     * break
     * 
     * pagos = Settle().walletTransactions(walletId)
     * solutionsAll = solutions ,debts, pagos
     * solution = Print().printSolutions(walletId, solutionsAll)
     * #print ("divisionWallet",solucionesAll)
     * return solution
     * 
     * 
     */
}
}