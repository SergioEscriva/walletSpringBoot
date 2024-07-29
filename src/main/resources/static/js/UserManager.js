///import { UrlPath } from './UrlPath.js'
//import { WalletManager } from './WalletManager.js'
//import { RequestDel } from './RequestDel.js'
import { RequestGet } from "./RequestGet.js";
//import { RequestPut } from './RequestPut.js'

export class UserManager {
  constructor() {
    this.initialiting();
  }

  static async init() {
    return new UserManager();
  }

  initialiting() {
    UserManager.permanentUserRead();
    document
      .querySelector("#button-add-username")
      .addEventListener("click", () => {
        this.addUserNew();
      });
  }

  async addUserNew() {
    const name_add = document.getElementById("name-username").value;
    const pin_add = document.getElementById("pin-username").value;
    let users = await RequestGet.getUsers();
    users.forEach((user) => {
      console.log("Guadando Usuario Permanente " + name_add + " -- " + pin_add);
      UserManager.permanentUserSave(name_add, pin_add);
    });

    document.getElementById("name-username").classList.add("changeInputError");
  }

  static async permanentUserSave(name_add, pin_add) {
    let userId = await RequestGet.getUserId(name_add);
    console.log("YYYYYeeeeepppppPP " + userId + " y " + pin_add);
    let objet = {
      Id: userId,
      Pin: pin_add,
    };
    objet = JSON.stringify(objet);
    localStorage.setItem("key", objet);
    document.location.href = "start.html";
  }

  static async permanentUserRead() {
    //let objet_load = localStorage.getItem("key");
    //objet_load = JSON.parse(objet_load);
    console.log("LocalStorage " + localStorage.length);
    if (localStorage.length == 0) {
      document.querySelector("#container").classList.remove("hidden");
    } else {
      document.location.href = "start.html";
    }
  }

  deleteSesion() {
    localStorage.clear();
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
}
