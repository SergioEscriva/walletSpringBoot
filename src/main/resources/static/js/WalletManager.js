import { RequestDel } from "./RequestDel.js";
import { RequestGet } from "./RequestGet.js";
import { RequestPost } from "./RequestPost.js";
import { RequestPut } from "./RequestPut.js";
import { TransactionManager } from "./TransactionManager.js";
//import { UserManager } from './UserManager.js'

export class WalletManager {
  constructor() {
    this.domElements = {};
  }

  async init() {
    WalletManager.getDomElements();
    await WalletManager.permanentUserRead();
    const proprietary =
      document.querySelector("#name-proprietary").dataset.idProprietary;
    const walletP = await RequestGet.getWallets(0);
    const wallets = await RequestGet.getWallets(proprietary);
    if (!wallets) return;
    this.fillWalletsSelector(wallets, this.domElements.walletSelector, walletP);
    this.addListeners();
  }

  static getDomElements() {
    this.domElements = {
      walletSelector: document.querySelector("#wallet-selector"),
      walletGroup: document.querySelector("#wallets-group"),
      walletAddGroup: document.querySelector("#add-wallet-group"),
      buttonsAddWGroup: document.querySelector("#buttonsAddW-group"),
      buttonsEditWGroup: document.querySelector("#buttonsEditW-group"),
      transactionsGroup: document.querySelector("#transactions-group"),
      transactions: document.querySelector("#transactions"),
      addTransactionGroup: document.querySelector("#add-transaction-group"),
      buttonsGroup: document.querySelector("#buttons-group"),
      buttonsWGroup: document.querySelector("#buttonW-group"),
      buttonsAddGroup: document.querySelector("#buttonsAdd-group"),
      buttonsDivGroup: document.querySelector("#buttonsDiv-group"),
      buttonEditGroupWM: document.querySelector("#buttonEditWM-Group"),
      division: document.querySelector("#division-show"),
      editTransactionGroup: document.querySelector("#edit-transaction-group"),
      divisionGroup: document.querySelector("#division-group"),
      balance: document.querySelector("#balance"),
      walletName: document.querySelector("#walletName"),
    };
  }

  async fillWalletsSelector(walletsData, algo, walletP) {
    const walletsSel = WalletManager.domElements.walletSelector;
    walletsSel.innerHTML = "";
    if (walletsData.length == 0) {
      walletsData = walletP;
    }
    walletsData.forEach((wallets) => {
      if (wallets.description == null) {
        wallets.description = "Sin descripción";
      }
      walletsSel.innerHTML += `
            <div id="wallet" data-wallet-id="${wallets.id}" data-wallet="wallets">
            <div id="walletId${wallets.id}"  data-wallet="wallets" data-wallet-id="${wallets.id}" data-wallet-name="${wallets.name}" class="cuadriculaWallet">
                <div id="wallet-name-id-${wallets.id}">${wallets.name}</div>
                <div id="wallet-description-id-${wallets.id}" style="font-size: 10px">${wallets.description}</div>
            </div></div>`;
    });
    console.log("fillWalletsSelector");
  }

  addListeners() {
    //Botones atras
    WalletManager.domElements.buttonsGroup
      .querySelector("#button-atras")
      .addEventListener("click", () => {
        window.location.reload();
      });
    WalletManager.domElements.buttonsAddGroup
      .querySelector("#button-atras")
      .addEventListener("click", () => {
        WalletManager.showTransac();
      });
    WalletManager.domElements.buttonsDivGroup
      .querySelector("#button-atras")
      .addEventListener("click", () => {
        WalletManager.showTransac();
      });
    /// Botones On
    WalletManager.domElements.walletGroup.classList.remove("hidden");
    WalletManager.domElements.buttonsWGroup
      .querySelector("#button-add")
      .addEventListener("click", () => {
        this.addWallet();
      });
    WalletManager.domElements.buttonsWGroup
      .querySelector("#button-edit")
      .addEventListener("click", () => {
        WalletManager.editWallet();
      });
    document.getElementById("funtionMenu").addEventListener("click", () => {
      this.menuConfig();
    });
    WalletManager.domElements.buttonsGroup
      .querySelector("#button-division")
      .addEventListener("click", () => {
        WalletManager.showDivision();
      });
    document.querySelector("#button-date").addEventListener("click", () => {
      TransactionManager.changeDate();
    });
    document
      .querySelector("#buttonEditWallete-atras")
      .addEventListener("click", () => {
        window.location.reload();
      });
    document
      .querySelector("#buttonEditWallete-category")
      .addEventListener("click", () => {
        WalletManager.editCategoryShow();
      });
    document
      .querySelector("#buttonEditWallete-user")
      .addEventListener("click", () => {
        WalletManager.editUser();
      });
    document
      .querySelector("#buttonEditW-atras")
      .addEventListener("click", () => {
        window.location.reload();
      });
    console.log("addWallet");
    document
      .querySelector("#buttonEditWtras-atras")
      .addEventListener("click", () => {
        window.location.reload();
      });
    /// Menú
    document.getElementById("menuCategorys").addEventListener("click", () => {
      WalletManager.editCategoryShow();
    });
    document.getElementById("menuUser").addEventListener("click", () => {
      WalletManager.editUser();
    });
    document.getElementById("menuSession").addEventListener("click", () => {
      localStorage.clear();
      document.location.href = "index.html";
    });
    document.getElementById("menuAbout").addEventListener("click", () => {
      document.getElementById("aboutText").classList.remove("hidden");
    });
    WalletManager.domElements.walletGroup.classList.remove("hidden");
    document.querySelectorAll("#wallet").forEach(function (element) {
      const walletId = element.dataset.walletId;
      document
        .querySelector("#walletId" + walletId)
        .addEventListener("click", () => {
          WalletManager.selectedWalletId(walletId);
        });
    });
    console.log("addListeners");
  }

  static addListenersSelect(selectedWalletId, selectedWalletName) {
    document
      .querySelector("#buttonWalletEdit-save")
      .addEventListener("click", () => {
        WalletManager.editWalletName(selectedWalletName);
      });
    document
      .querySelector("#buttonWalletEdit-del")
      .addEventListener("click", () => {
        WalletManager.delWallet(selectedWalletId, selectedWalletName);
      });
    document
      .querySelector("#buttonDelTransac")
      .addEventListener("click", () => {
        TransactionManager.delTransaction(selectedWalletId);
      });
    WalletManager.domElements.buttonsAddGroup
      .querySelector("#button-save")
      .addEventListener("click", () => {
        WalletManager.saveAddTransac(selectedWalletId);
      });
    WalletManager.domElements.buttonsAddGroup
      .querySelector("#button-edit-save")
      .addEventListener("click", () => {
        WalletManager.saveEditTransac(selectedWalletId);
      });
    WalletManager.domElements.buttonsGroup
      .querySelector("#button-add")
      .addEventListener("click", () => {
        this.showAddTransac(selectedWalletId);
      });
    console.log("addListenerSelect");
  }

  static selectedWalletId(walletId) {
    WalletManager.domElements.addTransactionGroup.classList.add("hidden");
    WalletManager.domElements.walletGroup.classList.add("hidden");
    const selectedWalletId = walletId;
    const selectedWalletName = document.querySelectorAll(
      "#walletId" + walletId
    )[0].firstElementChild.innerHTML;
    const selectWall = WalletManager.domElements.walletName;
    selectWall.innerHTML = "";
    WalletManager.domElements.walletName.innerHTML = `<div> Wallet ${selectedWalletName}</div>`;
    WalletManager.domElements.transactionsGroup.classList.remove("hidden");
    WalletManager.domElements.buttonsGroup.classList.remove("hidden");
    this.addListenersSelect(selectedWalletId, selectedWalletName);
    WalletManager.transactionsList(selectedWalletId);
    console.log("selectedWalletId");
  }

  static async transactionsList(selectedWalletId) {
    const transactionsData = await RequestGet.getTransactions(selectedWalletId);
    if (transactionsData.length == 0)
      return (
        alert("No hay Transacciones, Añade una..."),
        this.showAddTransac(selectedWalletId)
      );
    const balance = await RequestGet.getBalance(selectedWalletId);
    const balancemin = await RequestGet.getBalanceMin(selectedWalletId);
    const division = await RequestGet.getDivision(selectedWalletId);
    const membersData = await RequestGet.getMembers(selectedWalletId);
    const transactions = WalletManager.domElements.transactions;
    transactions.innerHTML = "";
    transactions.innerHTML += `
        <div id="transacIdid" title="0"><B>
        <div id="transacId0" title="0" class="cuadricula" >
            <div>Descripción</div>
            <div>Importe (€)</div>
            <div>Pagador</div>
            <div>Categoría</div>
            <div style="font-size: 10px">Participantes</div>
            <div>Fecha</div>
        </div></B></div>`;
    transactionsData.forEach(async (transaction) => {
      const userNameTransaction = await RequestGet.getNameById(
        transaction.userId
      );
      const categoryTransacction = await RequestGet.getCategoryById(
        transaction.category
      );
      let membersName = [];
      const members = await RequestGet.getMembers(selectedWalletId);
      members.forEach(async (member) => {
        let memberId = member.id;
        let transactionParticipants = transaction.participants;
        if (transactionParticipants.includes(memberId)) {
          membersName.push(member.nickname);
        }
      });

      transactions.innerHTML += `
            <div id="transacIdid" title="${transaction.id}">
            <div id="transacId${transaction.id}" title="${transaction.id}" class="cuadricula">
                <div>${transaction.description}</div>
                <div>${transaction.amount}€</div>
                <div>${userNameTransaction.username}</div>
                <div>${categoryTransacction.category}</div>
                <div style="font-size: 10px">${membersName}</div>
                <div>${transaction.date}</div>
            </div></div>`;
      console.log("transactionsList");
    });
    document.querySelectorAll("#transacIdid").forEach(function (element) {
      const transacId = element["title"];
      console.log("#transacId" + transacId);

      document
        .querySelector("#transacId" + transacId)
        .addEventListener("click", () => {
          console.log("holaAAAAAAAAAAAAAAAAAAAAAAAAAA " + selectedWalletId);
          WalletManager.showEditTransac(transacId, selectedWalletId);
        });
    });

    const balanceElement = WalletManager.domElements.balance;
    balanceElement.innerHTML = `
        <div class="cuadricula">
                <div>TOTAL:</div>
                <div><B>${balance}€</B></div>
                <div>Debería Pagar: <B>${balancemin.username}</B></div>
                <div><B>Miembros: </B></div>`;
    membersData.forEach((member) => {
      balanceElement.innerHTML += `
                · ${member.nickname}`;
    });

    const divisions = WalletManager.domElements.division;
    divisions.innerHTML = "";
    WalletManager.domElements.division.innerHTML = `<div>${division}</div><hr/>`;
    WalletManager.showTransac();
  }

  async userIdToUserName(userId) {
    //let userId = transactions.userId;
    let userName = await RequestGet.getNameById(userId);
    return userName;
  }

  menuConfig() {
    var x = document.getElementById("myLinks");
    if (x.style.display === "block") {
      x.style.display = "none";
      document.getElementById("container").classList.remove("hidden");
      document.getElementById("aboutText").classList.add("hidden");
    } else {
      x.style.display = "block";
      document.getElementById("container").classList.add("hidden");
      document.getElementById("aboutText").classList.add("hidden");
    }
  }

  addWallet() {
    WalletManager.domElements.walletGroup.classList.add("hidden");
    WalletManager.domElements.walletAddGroup.classList.remove("hidden");
    WalletManager.domElements.walletAddGroup
      .querySelector("#buttonWallet-save")
      .addEventListener("click", () => {
        this.addWalletNew();
      });
  }

  async addWalletNew() {
    const nameAdd = document.getElementById("nameAdd").value;
    const proprietaryId =
      document.querySelector("#name-proprietary").dataset.idProprietary;
    const proprietaryName =
      document.querySelector("#name-proprietary").dataset.nameProprietary;
    const respuesta = await RequestPost.postWallet(nameAdd, proprietaryId);

    if (respuesta === false) {
      document.getElementById("nameAdd").classList.add("changeInputError");
    } else {
      WalletManager.domElements.walletAddGroup.classList.add("hidden");
      const nameEdit = document.querySelector("#nameEdit");
      const fragmentW = document.createDocumentFragment();
      const wallet = await RequestGet.getWalletToId(nameAdd);
      const selectedWalletId = wallet[0]["id"];
      const selectedWalletName = nameAdd;
      await RequestPost.postMember(selectedWalletId, proprietaryName);
      document.getElementById("editwallet").classList.remove("hidden");
      document.getElementById("nameEditGroup").classList.add("hidden");
      document
        .getElementById("group-edit-name-wallet")
        .classList.remove("hidden");
      document.getElementById("edit-wallet-group").classList.remove("hidden");
      document.getElementById("nameEditNew").value = selectedWalletName;
      await WalletManager.editWalletMembers(selectedWalletId);
    }
    console.log("addWalletNew");
  }

  static async editWallet() {
    document.getElementById("nameEdit").innerHTML = "";
    const nameEdit = document.querySelector("#nameEdit");
    const fragmentW = document.createDocumentFragment();
    const proprietary =
      document.querySelector("#name-proprietary").dataset.idProprietary;
    const wallets = await RequestGet.getWallets(proprietary);
    wallets.forEach((wallet) => {
      const option = document.createElement("option");
      if (wallet.proprietary == proprietary) {
        document.querySelector("#share").disabled = false;
        option.value = wallet.id;
        option.text = wallet.name;
        option.dataset.description = wallet.description;
        option.dataset.share = wallet.share;
        fragmentW.appendChild(option);
      }
    });
    nameEdit.append(fragmentW);

    document.getElementById("wallets-group").classList.add("hidden");
    document.getElementById("edit-wallet-group").classList.remove("hidden");
    document
      .querySelector("#nameEdit")
      .addEventListener("change", async (event) => {
        const selectedWalletId = event.target.value;
        const selectedWalletName = event.target.selectedOptions[0].label;
        const description = event.target.selectedOptions[0].dataset.description;
        let shareWallet = event.target.selectedOptions[0].dataset.share;
        if (shareWallet == 1) {
          shareWallet = true;
        } else {
          shareWallet = false;
        }
        document.querySelector("#share").checked = shareWallet;
        document.getElementById("descriptionWalletEdit").value = description;
        document
          .getElementById("descriptionWalletEdit")
          .addEventListener("change", async (event) => {
            WalletManager.addDescriptionWallet(selectedWalletId);
          });

        document.getElementById("nameEditGroup").classList.add("hidden");
        document.getElementById("editwallet").classList.remove("hidden");
        document
          .getElementById("group-edit-name-wallet")
          .classList.remove("hidden");
        document.getElementById("nameEditNew").value = selectedWalletName;
        document.querySelector("#share").addEventListener("click", () => {
          WalletManager.shareWallet(selectedWalletId);
        });
        WalletManager.addListenersSelect(selectedWalletId, selectedWalletName);
        await WalletManager.editWalletMembers(
          selectedWalletId,
          selectedWalletName
        ).reload;
      });
    console.log("editWallet");
  }

  static async editWalletMembers(selectedWalletId) {
    document.getElementById("membersEdit-selector").classList.remove("hidden");
    document.getElementById("editwallet").classList.remove("hidden");
    const members = await RequestGet.getMembers(selectedWalletId);
    document.getElementById("membersEdit-selector").innerHTML = "";
    const membersSelector = document.getElementById("membersEdit-selector");
    members.forEach((members) => {
      membersSelector.innerHTML += `
            <div id='GroupMember${members.userId}' class='GroupMember' data-member-id="${members.userId}" data-member-name="${members.name}" data-member-nickname="${members.nickname}">
              
                <button id="buttonDel${members.userId}" class="mini-button">&#10062;</button>

                <input type="text" id="Member${members.userId}" value="${members.nickname}"></input>
               
                <button id="buttonEdit${members.userId}" class="mini-button-hidden">&#9989;</button>
            </div>`;
      const proprietary =
        document.querySelector("#name-proprietary").dataset.idProprietary;
      if (members.userId == proprietary) {
        document
          .querySelector("#buttonEdit" + members.userId)
          .classList.remove("mini-button-hidden");
        document
          .querySelector("#buttonEdit" + members.userId)
          .classList.add("mini-button");
      }
    });
    document
      .querySelector("#newMemberWallet-save")
      .addEventListener("click", () => {
        WalletManager.addMemberWalet(selectedWalletId);
      });

    document.querySelectorAll(".GroupMember").forEach(function (element) {
      const memberId = element.dataset.memberId;
      document
        .querySelector("#buttonDel" + memberId)
        .addEventListener("click", () => {
          WalletManager.delMember(memberId, selectedWalletId);
        });
      document
        .querySelector("#buttonEdit" + memberId)
        .addEventListener("click", () => {
          WalletManager.putMember(memberId);
        });
    });
  }

  static async shareWallet(walletId) {
    const share = document.getElementById("share").checked;
    if (share == true) {
      RequestPut.shareWallet(1, walletId);
    } else {
      RequestPut.shareWallet(0, walletId);
    }
  }

  static async delWallet(selectedWalletId, selectedWalletName) {
    var opcion = confirm(
      "¿Seguro que quieres Borrar el Wallet " +
        selectedWalletName +
        " y todas sus transacciones?"
    );
    if (opcion == true) {
      RequestDel.delWallet(selectedWalletId);
      window.location.reload();
    } else {
    }
    console.log("delWallet");
  }

  static async addDescriptionWallet(walletId) {
    const description = document.getElementById("descriptionWalletEdit").value;
    RequestPut.addDescription(walletId, description);
    document
      .getElementById("descriptionWalletEdit")
      .classList.add("changeInput");
  }

  static async putMember(memberId) {
    const nameNew = document.querySelector("#Member" + memberId).value;
    const nameOld = document.querySelector("#GroupMember" + memberId).dataset
      .memberNickname;
    const respuesta = await RequestPut.putNickname(nameOld, nameNew);
    if (respuesta === true) {
      document.getElementById("Member" + memberId).classList.add("changeInput");
    } else {
      document
        .getElementById("Member" + memberId)
        .classList.add("changeInputError");
    }
    document.getElementById("Member" + memberId).classList.add("changeInput");
    console.log("putMember");
  }

  static async editUser() {
    WalletManager.menuHideAll();
    document.querySelector("#edit-user-group").classList.remove("hidden");
    const memberId =
      document.querySelector("#name-proprietary").dataset.idProprietary;
    const memberName =
      document.querySelector("#name-proprietary").dataset.nameProprietary;
    const memberNickname =
      document.querySelector("#name-proprietary").dataset.nicknameProprietary;
    document.querySelector("#nickname-name").value = memberNickname;
    document.querySelector("#user-name").value = memberName;
    document
      .querySelector("#buttonEditNickname")
      .addEventListener("click", () => {
        WalletManager.putMember(memberId);
      });
    document.querySelector("#buttonDelUser").addEventListener("click", () => {
      WalletManager.delUser(memberId, memberName);
    });
    document.querySelector("#buttonEditUser").addEventListener("click", () => {
      WalletManager.putUser(memberName, memberId);
    });
    document.querySelector("#buttonEditPin").addEventListener("click", () => {
      WalletManager.putPin(memberId);
    });
    document
      .querySelector("#buttonEditUser-atras")
      .addEventListener("click", () => {
        document.querySelector("#edit-user-group").classList.add("hidden");
        document.location.href = "start.html";
      });
  }

  static async delUser(userId, userName) {
    const respuesta = await RequestDel.delUser(userId);
    if (respuesta === true) {
      document.location.href = "index.html";
    } else {
      document.getElementById("user-name").classList.add("changeInputError");
      alert(
        "El Miembro " +
          userName +
          " no se puede eliminar porque pertenece a algún Wallet, primero debe saldar cuentas y salir del Wallet."
      );
    }
    console.log("delMember");
  }

  static async putUser(nameOld, userId) {
    const pinNow = document.querySelector("#pin-now").value;
    if (pinNow.length == 0) {
      alert(
        "Debe introducir el Pin de Sesión Actual para modificar el Nombre de Inicio de Sesión"
      );
      document.getElementById("pin-now").classList.add("changeInputError");
    } else {
      const pin = await RequestGet.getPinId(userId);
      if (pin[0]["pin"] == pinNow) {
        const nameNew = document.querySelector("#user-name").value;
        const respuesta = await RequestPut.putUser(nameOld, nameNew);
        if (respuesta === true) {
          alert(
            "RECUERDE que en el próximo Inicio de Sesión su Usuario será " +
              nameNew
          );
          document.getElementById("user-name").classList.add("changeInput");
        } else {
          document
            .getElementById("user-name")
            .classList.add("changeInputError");
        }
        document.getElementById("user-name").classList.add("changeInput");
      } else {
        alert("Pin de Sesión Actual INCORRECTO");
        document.getElementById("pin-now").classList.add("changeInputError");
      }
    }
    console.log("putUser");
  }

  static async putPin(userId) {
    const pinNow = document.querySelector("#pin-now").value;
    const pinNew = document.querySelector("#pin-new").value;
    document.getElementById("pin-now").classList.remove("changeInputError");
    document.getElementById("pin-new").classList.remove("changeInputError");
    if (pinNow.length == 0) {
      alert("Debe introducir el Pin de Sesión Actual");
      document.getElementById("pin-now").classList.add("changeInputError");
      if (pinNew.length == 0) {
        alert("Debe introducir el Pin de Sesión Nuevo");
        document.getElementById("pin-new").classList.add("changeInputError");
      }
    } else {
      const pin = await RequestGet.getPinId(userId);
      if (pin[0]["pin"] == pinNow) {
        const respuesta = await RequestPut.putPin(pinNow, pinNew, userId);
        if (respuesta === true) {
          alert(
            "RECUERDE que en el próximo Inicio de Sesión su PIN será " + pinNew
          );
          document.getElementById("pin-new").classList.add("changeInput");
          document.getElementById("pin-now").classList.remove("changeInput");
          document.getElementById("pin-new").value = "";
          document.getElementById("pin-now").value = "";
        } else {
          document.getElementById("pin-new").classList.add("changeInputError");
        }
      } else {
        alert("Pin de Sesión Actual INCORRECTO");
        document.getElementById("pin-now").classList.add("changeInputError");
      }
      document.getElementById("pin-new").classList.add("changeInput");
    }
    console.log("putPin");
  }

  static async addMemberWalet(walletId) {
    const nameAdd = document.getElementById("newMemberWalletNew").value;
    const membersList = document.querySelectorAll(".GroupMember").length;
    await RequestPost.postMember(walletId, nameAdd);
    if (membersList == 0) {
      await RequestPut.putProprietary(walletId, nameAdd);
    }
    document.getElementById("membersEdit-selector").innerHTML = "";
    WalletManager.editWalletMembers(walletId);
    console.log("addMemberWalet");
  }

  static async delMember(memberId, walletId) {
    const memberName = document.querySelector("#Member" + memberId).value;
    const respuesta = await RequestDel.delMember(walletId, memberId);
    if (respuesta === true) {
      document.getElementById("GroupMember" + memberId).classList.add("hidden");
      document
        .getElementById("membersEdit-selector")
        .classList.remove("hidden");
    } else {
      document
        .getElementById("Member" + memberId)
        .classList.add("changeInputError");
      alert(
        "El Miembro " +
          memberName +
          " no se puede eliminar porque tiene pendiente saldar cuentas."
      );
    }
    console.log("delMember");
  }

  static async editWalletName(nameOld) {
    const nameNew = document.getElementById("nameEditNew").value;
    const respuesta = await RequestPut.putWallet(nameOld, nameNew);

    if (respuesta === true) {
      document.getElementById("nameEditNew").classList.add("changeInput");
    } else {
      document.getElementById("nameEditNew").classList.add("changeInputError");
    }
    document.getElementById("nameEditNew").classList.add("changeInput");
    console.log("editWalletName");
  }

  static async addMember() {
    const nameAdd = document.getElementById("nameAdd").value;
    const wallet = await RequestGet.getWalletToId(nameAdd);
    const walletId = wallet[0]["id"];
    const memberAdd = document.getElementById("newAdd").value;
    RequestPost.postMember(walletId, memberAdd);
    WalletManager.fillMembersSelector([memberAdd]);
    document.getElementById("newAdd").value = "";
    console.log("addMember");
    return memberAdd;
  }

  fillMembersSelector(members) {
    const payersSelector = document.querySelector("#membersAdd-selector");
    const fragment = document.createDocumentFragment();
    members.forEach((members) => {
      const option = document.createElement("option");
      option.value = members;
      option.text = members;
      fragment.appendChild(option);
    });
    payersSelector.append(fragment);
    document.getElementById("membersAdd-selector").classList.remove("hidden");
    console.log("fillMembersSelector");
    return payersSelector;
  }

  static async checkInput(elemetById) {
    const nameNew = document.getElementById(elemetById).value;
    document.getElementById(elemetById).classList.remove("changeInputError");
    if (nameNew == "") {
      document.getElementById(elemetById).classList.add("changeInputError");
      document.getElementById("button-edit-save").disabled = true;
      document.getElementById("button-save").disabled = true;
    } else {
      document.getElementById(elemetById).classList.remove("changeInputError");
      document.getElementById("button-edit-save").disabled = false;
      document.getElementById("button-save").disabled = false;
    }

    console.log("checkInput");
  }

  static showTransac() {
    WalletManager.hiddenButtonEdit();
    WalletManager.exitTransaction();
    WalletManager.domElements.walletGroup.classList.add("hidden");
    WalletManager.domElements.divisionGroup.classList.add("hidden");
    WalletManager.domElements.addTransactionGroup.classList.add("hidden");
    WalletManager.domElements.editTransactionGroup.classList.add("hidden");
    WalletManager.domElements.transactionsGroup.classList.remove("hidden");
    console.log("showTransac");
  }

  static async saveAddTransac(selectedWalletId) {
    this.hiddenButtonEdit();
    const resultado = await TransactionManager.addTransac(selectedWalletId);
    if (resultado === true) {
      this.transactionsList(selectedWalletId);
    }
    console.log("saveAddTransac");
  }

  static async saveEditTransac(selectedWalletId) {
    WalletManager.hiddenButtonEdit();
    const resultado = await TransactionManager.editTransac(selectedWalletId);
    if (resultado === true) {
      WalletManager.transactionsList(selectedWalletId);
      //this.showTransac()
    }
    console.log("saveEditTransac");
  }

  static showAddTransac(selectedWalletId) {
    WalletManager.hiddenButtonEdit();
    document.getElementById("button-save").disabled = true;
    WalletManager.domElements.walletGroup.classList.add("hidden");
    WalletManager.domElements.transactionsGroup.classList.add("hidden");
    WalletManager.domElements.addTransactionGroup.classList.remove("hidden");
    document.querySelector("#tituloAñadir").classList.remove("hidden");
    document.querySelector("#button-save").classList.remove("hidden");
    document.querySelector("#tituloEditar").classList.add("hidden");
    document.querySelector("#button-edit-save").classList.add("hidden");
    document.querySelector("#buttonDelTransac").classList.add("hidden");
    document.querySelector("#participants-selector").value = "Todos";
    document.getElementById("descriptionAdd").addEventListener("blur", () => {
      WalletManager.checkInput("descriptionAdd");
    });
    document.getElementById("amountAdd").addEventListener("blur", () => {
      WalletManager.checkInput("amountAdd");
    });
    document.getElementById("descriptionAdd").focus();
    document.querySelector("#adddate-selector").value =
      TransactionManager.dateTimeNow();
    TransactionManager.init(selectedWalletId);
    console.log("showAddTransac");
  }

  static showEditTransac(transacId, selectedWalletId) {
    document.getElementById("button-edit-save").disabled = true;
    document.querySelector("#wallets-group").classList.add("hidden");
    document.querySelector("#transactions-group").classList.add("hidden");
    document.querySelector("#add-transaction-group").classList.remove("hidden");
    document.querySelector("#edit-transaction-group").classList.add("hidden");
    document.querySelector("#division-group").classList.add("hidden");
    document.querySelector("#tituloAñadir").classList.add("hidden");
    document.querySelector("#button-save").classList.add("hidden");
    document.querySelector("#tituloEditar").classList.remove("hidden");
    document.querySelector("#button-edit-save").classList.remove("hidden");
    document.querySelector("#buttonDelTransac").classList.remove("hidden");
    document
      .getElementById("descriptionAdd")
      .classList.remove("changeInputError");
    document.getElementById("amountAdd").classList.remove("changeInputError");
    document.getElementById("descriptionAdd").addEventListener("blur", () => {
      WalletManager.checkInput("descriptionAdd");
    });
    document.getElementById("amountAdd").addEventListener("blur", () => {
      WalletManager.checkInput("amountAdd");
    });
    document.getElementById("descriptionAdd").focus();
    TransactionManager.initTransaction(transacId, selectedWalletId);
    console.log("showEditTransac");
  }

  static async menuHideAll() {
    document.querySelector("#wallets-group").classList.add("hidden");
    document.querySelector("#transactions-group").classList.add("hidden");
    document.querySelector("#add-transaction-group").classList.add("hidden");
    document.querySelector("#add-wallet-group").classList.add("hidden");
    document.querySelector("#edit-wallet-group").classList.add("hidden");
    document.querySelector("#edit-transaction-group").classList.add("hidden");
    document.querySelector("#division-group").classList.add("hidden");
    document.querySelector("#tituloAñadir").classList.add("hidden");
    document.querySelector("#button-save").classList.add("hidden");
    document.querySelector("#tituloEditar").classList.add("hidden");
    document.querySelector("#button-edit-save").classList.add("hidden");
    document.querySelector("#buttonDelTransac").classList.add("hidden");
    document.getElementById("container").classList.remove("hidden");
    document.getElementById("aboutText").classList.add("hidden");
    document.getElementById("myLinks").style.display = "none";
    console.log("menuHideAll");
  }

  static async editCategoryShow() {
    WalletManager.menuHideAll();
    document
      .querySelector("#button-editCat-atras")
      .addEventListener("click", () => {
        window.location.reload();
      });
    document
      .querySelector("#newCategory-save")
      .addEventListener("click", () => {
        const categoryNew = document.querySelector("#newCategory").value;
        TransactionManager.saveCategory(categoryNew);
        WalletManager.editCategoryShow();
      });

    TransactionManager.editCategory();
    console.log("showEditCategory");
  }

  static showDivision() {
    WalletManager.domElements.walletGroup.classList.add("hidden");
    WalletManager.domElements.transactionsGroup.classList.add("hidden");
    WalletManager.domElements.addTransactionGroup.classList.add("hidden");
    WalletManager.domElements.editTransactionGroup.classList.add("hidden");
    WalletManager.domElements.divisionGroup.classList.remove("hidden");
    console.log("showDivision");
  }

  showButtonEdit() {
    const transactions = document.querySelectorAll(".mini-button-hidden");
    const transLength = transactions.length;
    for (let i = 0; i < transLength; i++) {
      var transactio = transactions[i];
      transactio.className = "mini-button";
    }
    console.log("showButtonEdit");
  }

  static hiddenButtonEdit() {
    const transactions = document.querySelectorAll(".mini-button");
    const transLength = transactions.length;
    for (let i = 0; i < transLength; i++) {
      var transactio = transactions[i];
      transactio.className = "mini-button-hidden";
    }
    console.log("hiddenButtonEdit");
  }

  static exitTransaction() {
    document.getElementById("descriptionAdd").value = "";
    document.getElementById("amountAdd").value = "";
    document.querySelector("#category-selected").value = "";
    document.querySelector("#payers-selector").value = "";
    console.log("exitTransaction");
  }

  static async acciondb() {
    var myFile = document.querySelector("#file").files[0];
    var form = new FormData();
    form.append("file", myFile);
    const response = RequestPost.postFile(form);
    const walletManager = new WalletManager();
    await walletManager.init();
    document.querySelector("#file").classList.add("hidden");
  }

  static async permanentUserRead() {
    let objetLoad = localStorage.getItem("key");
    objetLoad = JSON.parse(objetLoad);
    const idProprietary = objetLoad["Id"];
    if (idProprietary == undefined) {
      localStorage.clear();
      document.location.href = "index.html";
    } else {
      let nameProprietary = await RequestGet.getNameById(idProprietary);
      document.querySelector(
        "#proprietary-wallets"
      ).innerHTML = `<div id="name-proprietary" data-name-proprietary='${nameProprietary.username}' data-id-proprietary='${idProprietary}' data-nickname-proprietary='${nameProprietary.nickname}'>Wallets de ${nameProprietary.username}</div><div>Alias ${nameProprietary.nickname}</div>`;
      console.log("permanentUserRead");
    }
  }
}
