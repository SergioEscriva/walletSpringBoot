///import { UrlPath } from './UrlPath.js'
//import { WalletManager } from './WalletManager.js'
import { RequestDel } from "./RequestDel.js";
import { RequestGet } from "./RequestGet.js";
import { RequestPost } from "./RequestPost.js";
import { RequestPut } from "./RequestPut.js";
//import { RequestPut } from './RequestPut.js'

export class TransactionManager {
  constructor(walletId, categories, members) {
    this.initialiting(walletId, categories, members);
  }

  static async init(walletId) {
    const categories = await RequestGet.getCategories();
    const members = await RequestGet.getMembers(walletId);
    return new TransactionManager(walletId, categories, members);
  }

  initialiting(walletId, categories, members) {
    TransactionManager.fillCategorySelector(categories);
    TransactionManager.fillMembersSelector(members);
    TransactionManager.fillParticipantsSelector(members);
  }

  static fillMembersSelector(members) {
    const payersSelector = document.querySelector("#payers-selector");
    document.querySelector("#payers-selector").innerHTML = "";
    const fragment = document.createDocumentFragment();
    members.forEach((members) => {
      const option = document.createElement("option");
      option.id = members.id;
      option.value = members.username;
      option.text = members.nickname;
      fragment.appendChild(option);
    });

    payersSelector.append(fragment);
  }

  static fillParticipantsSelector(members) {
    const participants = document.getElementById("participants-selector").value;
    const participantsSelector = document.querySelector(
      "#participants-selector"
    );
    document.querySelector("#participants-selector").innerHTML = "";
    members.forEach((participant) => {
      participantsSelector.innerHTML += `
            <input id=${participant.id} name="${participant.nickname}" type="checkbox" value= ${participant.id}>${participant.nickname}</input>
            `;
    });
    if (participants == "Todos") {
      members.forEach((participantCheck) => {
        document.querySelector(
          '#participants-selector > [name="' + participantCheck.nickname + '"]'
        ).checked = true;
      });
    } else {
      participants.forEach((participantCheck) => {
        document.querySelector(
          '#participants-selector > [name="' + participantCheck + '"]'
        ).checked = true;
      });
    }
  }

  static fillCategorySelector(category) {
    const categorySelector = document.querySelector("#category-selector");
    categorySelector.innerHTML = "";
    //document.querySelector("#category-selector").innerHTML = "";
    const fragment = document.createDocumentFragment();
    //category.unshift({ id: 1, category: "Varios" });
    category.forEach((categories) => {
      const option = document.createElement("option");
      option.value = categories.category;
      option.text = categories.category;
      fragment.appendChild(option);
    });

    categorySelector.append(fragment);
  }

  static async editCategory() {
    const categories = await RequestGet.getCategories();
    document.getElementById("category-edit-group").classList.remove("hidden");
    //document.getElementById('editwallet').classList.remove('hidden')
    document.getElementById("category-edit-selector").innerHTML = "";
    const categoriesSelector = document.getElementById(
      "category-edit-selector"
    );
    categories.forEach((category) => {
      categoriesSelector.innerHTML += `
                <div id="GroupCategory${category.id}" class="GroupCategory"  data-category-id="${category.id}">
                <button id="buttonDelCat${category.id}" class="mini-button">&#10062;</button>

                <input type="text" id="Category${category.id}" value="${category.category}"></input>
                
                <button id="buttonEditCat${category.id}" class="mini-button">&#9989;</button>
                </div>
            `;
    });
    document.querySelectorAll(".GroupCategory").forEach(function (element) {
      const categoryId = element.dataset.categoryId;
      const categoryOld = document.querySelector(
        "#Category" + categoryId
      ).value;
      document
        .querySelector("#buttonDelCat" + categoryId)
        .addEventListener("click", () => {
          document
            .querySelector("#GroupCategory" + categoryId)
            .classList.add("hidden");
          RequestDel.delCategory(categoryId);
        });
      document
        .querySelector("#buttonEditCat" + categoryId)
        .addEventListener("click", () => {
          document
            .getElementById("Category" + categoryId)
            .classList.add("changeInput");
          const categoryNew = document.querySelector(
            "#Category" + categoryId
          ).value;
          RequestPut.editCategory(categoryOld, categoryNew);
        });
    });

    console.log("editCategory");
  }

  static async addTransac(selectedWalletId) {
    var descriptionValue = document.getElementById("descriptionAdd").value;
    var amountValue = document.getElementById("amountAdd").value;
    const categorySelector = document.querySelector("#category-selected").value;

    const categoryId = await RequestGet.getCategoryByName(categorySelector);

    var checked = document.querySelectorAll("#participants-selector :checked");
    const selectedParticipantAdd = [...checked].map((option) => option.value);
    var selectedPayerAdd = document.querySelector("#payers-selector").value;
    const selectedPayer = await RequestGet.getUserIdByName(selectedPayerAdd);
    const dateMemberAdd = document.querySelector("#adddate-selector").value;
    var selectedParticipants = String(selectedParticipantAdd);

    const transactionAdd = {
      category: categoryId.id,
      description: descriptionValue,
      amount: amountValue,
      userId: selectedPayer,
      date: dateMemberAdd,
      walletId: selectedWalletId,
      participants: selectedParticipants,
    };
    TransactionManager.saveCategory(categorySelector);
    const validate = TransactionManager.validateParticipants(
      selectedParticipantAdd
    );
    if (validate === true) {
      const request = await RequestPost.postTransaction(transactionAdd);
      TransactionManager.initTransaction(0, selectedWalletId);
      return request;
    }
  }

  static async initTransaction(transacId, selectedWalletId) {
    TransactionManager.init(selectedWalletId);
    const transactionData = await RequestGet.getTransaction(transacId);
    transactionData.forEach((transaction) => {
      document.getElementById("amountAdd").value = transaction.amount;
      document.getElementById("descriptionAdd").value = transaction.description;
      document.getElementById("participants-selector").value =
        transaction.participants;
      document.getElementById("category-selected").value = transaction.category;
      document.getElementById("transacId").value = transacId;
      document.getElementById("adddate-selector").value = transaction.date;
    });
  }

  static async editTransac(selectedWalletId) {
    var transacId = document.getElementById("transacId").value;
    var descriptionValue = document.getElementById("descriptionAdd").value;
    var amountValue = document.getElementById("amountAdd").value;
    var selectedCategoryAdd =
      document.querySelector("#category-selected").value;
    var checked = document.querySelectorAll("#participants-selector :checked");
    const selectedParticipantAdd = [...checked].map((option) => option.value);
    var selectedPayerAdd = document.querySelector("#payers-selector").value;
    const dateMemberAdd = document.querySelector("#adddate-selector").value;
    //const walletId = document.querySelector('#wallet-selector').value;
    const transactionAdd = {
      id: transacId,
      category: selectedCategoryAdd,
      description: descriptionValue,
      amount: amountValue,
      userId: selectedPayerAdd,
      date: dateMemberAdd,
      walletId: selectedWalletId,
      participants: selectedParticipantAdd,
    };
    TransactionManager.saveCategory(selectedCategoryAdd);
    const validate = TransactionManager.validateParticipants(
      selectedParticipantAdd
    );
    if (validate === true) {
      const request = await RequestPut.editTransaction(transactionAdd);
      TransactionManager.initTransaction(0, selectedWalletId);
      return request;
    }
    console.log("editTransac");
  }

  static async saveCategory(categorySelected) {
    const categories = await RequestGet.getCategories();
    var categorys = [];
    for (var i = 0; i < categories.length; i++) {
      let category = categories[i]["category"];
      categorys.push(category);
    }
    const resultado = categorys.includes(categorySelected);
    if (resultado == false) {
      RequestPost.postCategory(categorySelected);
    }
    console.log("saveCategory");
  }

  static validateParticipants(selectedParticipants) {
    if (selectedParticipants.length === 0) {
      alert("Debe seleccionar al menos un participante");
      return false;
    } else return true;
  }

  static async delTransaction(selectedWalletId) {
    var transacId = document.getElementById("transacId").value;
    const transactionData = await RequestGet.getTransaction(transacId);
    const description = transactionData[0]["description"];
    const amount = transactionData[0].amount;
    var mensaje;
    var opcion = confirm(
      "¿Seguro que quieres Borrar la transacción de (" +
        description +
        " con un valor de " +
        amount +
        "€) ?"
    );
    if (opcion == true) {
      RequestDel.delTransac(transacId);
      document.getElementById("transacId" + transacId).className = "hidden";
    }
  }

  static exitTransaction() {
    document.getElementById("descriptionAdd").value = "";
    document.getElementById("amountAdd").value = "";
    document.querySelector("#category-selected").value = "";
    document.querySelector("#payers-selector").value = "";
  }

  static dateTimeNow() {
    var now = new Date();
    //var date = now.getDate() + '/' + ( now.getMonth() + 1 ) + '/' + now.getFullYear()
    var date =
      now.getFullYear() + "/" + (now.getMonth() + 1) + "/" + now.getDate();
    var time = now.getHours() + ":" + now.getMinutes() + ":" + now.getSeconds();
    var dateTime = date + ", " + time;
    return dateTime;
  }

  static changeDate() {
    document.querySelector("#adddate-selector").classList.add("hidden");
    document.querySelector("#button-date").classList.add("hidden");
    document.querySelector("#formDatetime").classList.remove("hidden");
    document.querySelector("#formDatetime").addEventListener("change", () => {
      let valor = document.querySelector("#formDatetime").value;
      valor = valor.replace(/-/g, "/");
      valor = valor.replace(/T/g, ", ");
      document.querySelector("#adddate-selector").value = valor;
      document.querySelector("#adddate-selector").classList.remove("hidden");
      document.querySelector("#formDatetime").classList.add("hidden");
      document.querySelector("#button-date").classList.remove("hidden");
    });
  }
}
