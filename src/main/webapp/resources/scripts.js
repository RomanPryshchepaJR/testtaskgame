function loadContent(root, suffix, currentPage) {

    let objects = JSON.parse(Get(root + "/rest/players" + suffix).responseText);
    let playersCount = Get(root + "/rest/players/count" + suffix).responseText;
    document.getElementById("count").innerText = "Players found: " + playersCount;
    let table = document.getElementById("mainTable");
    table.innerHTML = "";
    createPaging(document.getElementById("limit").value, playersCount, currentPage);

    for (let i = 0; i < objects.length; i++) {

        let tr = document.createElement("tr");
        let th = document.createElement("th");
        th.setAttribute("scope", "row");
        th.appendChild(document.createTextNode(objects[i].id));
        tr.appendChild(th);
        let td1 = document.createElement("td");
        td1.appendChild(document.createTextNode(objects[i].name));
        tr.appendChild(td1);
        let td2 = document.createElement("td");
        td2.appendChild(document.createTextNode(objects[i].title));
        tr.appendChild(td2);
        let td3 = document.createElement("td");
        let race = objects[i].race.charAt(0).toUpperCase() + objects[i].race.slice(1).toLowerCase();
        td3.appendChild(document.createTextNode(race));
        tr.appendChild(td3);
        let td4 = document.createElement("td");
        let profession = objects[i].profession.charAt(0).toUpperCase() + objects[i].profession.slice(1).toLowerCase();
        td4.appendChild(document.createTextNode(profession));
        tr.appendChild(td4);
        let td5 = document.createElement("td");
        td5.appendChild(document.createTextNode(objects[i].experience));
        tr.appendChild(td5);
        let td6 = document.createElement("td");
        td6.appendChild(document.createTextNode(objects[i].level));
        tr.appendChild(td6);
        let td7 = document.createElement("td");
        td7.appendChild(document.createTextNode(objects[i].untilNextLevel));
        tr.appendChild(td7);
        let td8 = document.createElement("td");
        let birthday = new Date();
        birthday.setTime(objects[i].birthday);
        td8.appendChild(document.createTextNode(birthday.toDateString()));
        tr.appendChild(td8);
        let banned;
        if (objects[i].banned) {
            banned = "banned";
        } else {
            banned = "active";
        }
        let td9 = document.createElement("td");
        td9.appendChild(document.createTextNode(banned));
        tr.appendChild(td9);
        let warButton = document.createElement("button");
        warButton.setAttribute("type", "button");
        warButton.setAttribute("class", "btn btn-warning btn-sm");
        warButton.appendChild(document.createTextNode("Edit"));
        warButton.addEventListener("click", function () {
            editButtonClick(root, tr, objects[i].id)
        });
        let dangerButton = document.createElement("button");
        dangerButton.setAttribute("type", "button");
        dangerButton.setAttribute("class", "btn btn-danger btn-sm");
        dangerButton.appendChild(document.createTextNode("Delete"));
        dangerButton.addEventListener("click", function () {
            processDelete(root, objects[i].id)
        });
        let td10 = document.createElement("td");
        td10.appendChild(warButton);
        tr.appendChild(td10);
        let td11 = document.createElement("td");
        td11.appendChild(dangerButton);
        tr.appendChild(td11);
        table.appendChild(tr);
    }
    window.scrollTo(500, 100);
}

function Get(requestUrl) {
    let Httpreq = new XMLHttpRequest(); // a new request
    Httpreq.open("GET", requestUrl, false);
    Httpreq.send(null);
    if (Httpreq.status === 400) {
        $('#error-text').text("Bad request to GET " + requestUrl);
        $('#myModal').modal('show');
    }
    if (Httpreq.status === 404) {
        $('#error-text').text("Not found GET " + requestUrl);
        $('#myModal').modal('show');
    }
    return Httpreq;
}

function post(requestUrl, body) {
    let Httpreq = new XMLHttpRequest(); // a new request
    Httpreq.open("POST", requestUrl, false);
    Httpreq.setRequestHeader("Content-type", "application/json;charset=UTF-8");
    Httpreq.send(body);
    if (Httpreq.status === 400) {
        $('#error-text').text("Bad request to POST " + requestUrl);
        $('#myModal').modal('show');
    }
    if (Httpreq.status === 404) {
        $('#error-text').text("Not found POST " + requestUrl);
        $('#myModal').modal('show');
    }
    return Httpreq;
}

function Delete(requestUrl) {
    let Httpreq = new XMLHttpRequest(); // a new request
    Httpreq.open("DELETE", requestUrl, false);
    Httpreq.send(null);
    console.log(Httpreq.status);
    if (Httpreq.status === 400) {
        $('#error-text').text("Bad request to DELETE " + requestUrl);
        $('#myModal').modal('show');
    }
    if (Httpreq.status === 404) {
        $('#error-text').text("Not found DELETE " + requestUrl);
        $('#myModal').modal('show');
    }
    return Httpreq;
}

function processSearch(root, currentPage) {
    let name = document.getElementById("inputName").value;
    let title = document.getElementById("inputTitle").value;
    let birthdayAfter = new Date(document.getElementById("inputBirthdayAfter").value).getTime();
    if (isNaN(birthdayAfter)) {
        birthdayAfter = "";
    }
    let birthdayBefore = new Date(document.getElementById("inputBirthdayBefore").value).getTime();
    if (isNaN(birthdayBefore)) {
        birthdayBefore = "";
    }
    let experienceMin = document.getElementById("inputExperienceMin").value;
    let experienceMax = document.getElementById("inputExperienceMax").value;
    let levelMin = document.getElementById("inputLevelMin").value;
    let levelMax = document.getElementById("inputLevelMax").value;
    let race = document.getElementById("inputRace").value;
    let profession = document.getElementById("inputProfession").value;
    let order = document.getElementById("order").value;
    let banned = null;
    let limit = document.getElementById("limit").value;
    if (document.getElementById("inlineRadio2").checked) {
        banned = false;
    } else if (document.getElementById("inlineRadio3").checked) {
        banned = true;
    }
    let suffix = "?";
    if (name !== "") {
        suffix += "name=" + name;
    }
    if (title !== "") {
        suffix += "&title=" + title;
    }
    if (race !== "Any") {
        suffix += "&race=" + race.toUpperCase();
    }
    if (profession !== "Any") {
        suffix += "&profession=" + profession.toUpperCase();
    }
    if (birthdayAfter !== "") {
        suffix += "&after=" + birthdayAfter;
    }
    if (birthdayBefore !== "") {
        suffix += "&before=" + birthdayBefore;
    }
    if (banned !== null) {
        suffix += "&banned=" + banned;
    }
    if (experienceMin !== "") {
        suffix += "&minExperience=" + experienceMin;
    }
    if (experienceMax !== "") {
        suffix += "&maxExperience=" + experienceMax;
    }
    if (levelMin !== "") {
        suffix += "&minLevel=" + levelMin;
    }
    if (levelMax !== "") {
        suffix += "&maxLevel=" + levelMax;
    }

    suffix += "&pageNumber=" + (+currentPage - 1);
    suffix += "&pageSize=" + +limit;

    console.log(limit);

    suffix += "&order=" + order.toUpperCase();
    loadContent(root, suffix, currentPage);
}

function createPaging(playersInPage, playersSummary, currentPage) {
    let paggingBar = document.getElementById("pagging-bar");
    paggingBar.innerHTML = "";
    let pagesCount = playersSummary / playersInPage;
    if (pagesCount > 1) {

        for (let i = 0; i < pagesCount; i++) {
            let li = document.createElement("li");
            if (i === currentPage - 1) {
                li.setAttribute("class", "page-item disabled");
            } else {
                li.setAttribute("class", "page-item");
            }
            let a = document.createElement("a");
            a.setAttribute("class", "page-link");
            a.setAttribute("href", "#");
            let root = document.getElementById("root").getAttribute("about");
            a.setAttribute("onclick", "processSearch('" + root + "', " + (i + 1) + ")");
            a.appendChild(document.createTextNode(i + 1));
            li.appendChild(a);
            paggingBar.appendChild(li);
        }
    }
}

function editButtonClick(root, element, id) {
    let objectToUpdate = JSON.parse(Get(root + "/rest/players/" + id).responseText);
    if (document.body.contains(document.getElementById("update" + id))) {
        document.getElementById("update" + id).remove();
        return;
    }
    let tr = document.createElement("tr");
    tr.setAttribute("id", "update" + objectToUpdate.id);

    let th = document.createElement("th");
    th.setAttribute("scope", "row");
    th.appendChild(document.createTextNode(""));
    tr.appendChild(th);

    let td1 = document.createElement("td");
    let nameInput = document.createElement("input");
    nameInput.setAttribute("type", "text");
    nameInput.setAttribute("class", "form-control");
    nameInput.setAttribute("size", "10");
    nameInput.setAttribute("style", "font-family:monospace");
    nameInput.setAttribute("id", "updateName" + objectToUpdate.id);
    nameInput.setAttribute("value", objectToUpdate.name);
    td1.appendChild(nameInput);
    tr.appendChild(td1);

    let td2 = document.createElement("td");
    let titleInput = document.createElement("input");
    titleInput.setAttribute("type", "text");
    titleInput.setAttribute("class", "form-control input-sm");
    titleInput.setAttribute("size", "6");
    titleInput.setAttribute("style", "font-family:monospace");
    titleInput.setAttribute("id", "updateTitle" + objectToUpdate.id);
    titleInput.setAttribute("value", objectToUpdate.title);
    td2.appendChild(titleInput);
    tr.appendChild(td2);

    let td3 = document.createElement("td");
    let raceInput = document.createElement("select");
    raceInput.setAttribute("class", "form-control input-sm");
    raceInput.setAttribute("id", "updateRace" + objectToUpdate.id);
    raceInput.setAttribute("style", "font-family:monospace");
    let race = ["Human", "Dwarf", "Elf", "Giant", "Orc", "Troll", "Hobbit"];
    for (let i = 0; i < race.length; i++) {
        let option = document.createElement("option");
        if (race[i].toUpperCase() === objectToUpdate.race.toUpperCase()) {
            option.selected = true;
        }
        option.appendChild(document.createTextNode(race[i]));
        raceInput.appendChild(option);
    }
    td3.appendChild(raceInput);
    tr.appendChild(td3);

    let td4 = document.createElement("td");
    let professionInput = document.createElement("select");
    professionInput.setAttribute("class", "form-control input-sm");
    professionInput.setAttribute("id", "updateProfession" + objectToUpdate.id);
    professionInput.setAttribute("style", "font-family:monospace");
    let profession = ["Warrior", "Rogue", "Sorcerer", "Cleric", "Paladin", "Nazgul", "Warlock", "Druid"];
    for (let i = 0; i < profession.length; i++) {
        let option = document.createElement("option");
        if (profession[i].toUpperCase() === objectToUpdate.profession.toUpperCase()) {
            option.selected = true;
        }
        option.appendChild(document.createTextNode(profession[i]));
        professionInput.appendChild(option);
    }
    td4.appendChild(professionInput);
    tr.appendChild(td4);

    let td5 = document.createElement("td");
    let experienceInput = document.createElement("input");
    experienceInput.setAttribute("type", "number");
    experienceInput.setAttribute("min", "0");
    experienceInput.setAttribute("max", "10737405");
    experienceInput.setAttribute("size", "10");
    experienceInput.setAttribute("style", "font-family:monospace");
    experienceInput.setAttribute("step", "1000");
    experienceInput.setAttribute("class", "form-control");
    experienceInput.setAttribute("id", "updateExperience" + objectToUpdate.id);
    experienceInput.setAttribute("value", objectToUpdate.experience);
    td5.appendChild(experienceInput);
    tr.appendChild(td5);

    let td6 = document.createElement("td");
    td6.appendChild(document.createTextNode(objectToUpdate.level));
    tr.appendChild(td6);

    let td7 = document.createElement("td");
    td7.appendChild(document.createTextNode(objectToUpdate.untilNextLevel));
    tr.appendChild(td7);

    let td8 = document.createElement("td");
    let birthdayInput = document.createElement("input");
    birthdayInput.setAttribute("type", "date");
    birthdayInput.setAttribute("size", "4");
    birthdayInput.setAttribute("style", "font-family:monospace");
    birthdayInput.setAttribute("class", "form-control");
    birthdayInput.setAttribute("id", "updateBirthday" + objectToUpdate.id);
    birthdayInput.setAttribute("value", new Date(objectToUpdate.birthday).toISOString().substring(0, 10));
    td8.appendChild(birthdayInput);
    tr.appendChild(td8);

    let td9 = document.createElement("td");
    let bannedInput = document.createElement("select");
    bannedInput.setAttribute("class", "form-control input-sm");
    bannedInput.setAttribute("style", "font-family:monospace");
    bannedInput.setAttribute("id", "updateBanned" + objectToUpdate.id);
    let bannedType = ["active", "banned"];
    for (let i = 0; i < bannedType.length; i++) {
        let option = document.createElement("option");
        if (objectToUpdate.banned === true && bannedType[i] === "banned") {
            option.selected = true;
        }
        if (objectToUpdate.banned === false && bannedType[i] === "active") {
            option.selected = true;
        }
        option.appendChild(document.createTextNode(bannedType[i]));
        bannedInput.appendChild(option);
    }
    td9.appendChild(bannedInput);
    tr.appendChild(td9);

    let td10 = document.createElement("td");
    td8.appendChild(document.createTextNode(""));
    tr.appendChild(td10);

    let td11 = document.createElement("td");
    let saveButton = document.createElement("button");
    saveButton.setAttribute("type", "button");
    saveButton.setAttribute("class", "btn btn-success btn-sm");
    saveButton.addEventListener("click", function () {
        sendUpdate(root, objectToUpdate.id)
    });
    saveButton.appendChild(document.createTextNode("Save"));
    td11.appendChild(saveButton);
    tr.appendChild(td11);

    element.insertAdjacentElement("afterEnd", tr);
}

function sendUpdate(root, id) {
    let body = {};
    body.name = document.getElementById("updateName" + id).value;
    body.title = document.getElementById("updateTitle" + id).value;
    body.race = document.getElementById("updateRace" + id).value.toUpperCase();
    body.profession = document.getElementById("updateProfession" + id).value.toUpperCase();
    body.birthday = new Date(document.getElementById("updateBirthday" + id).value).getTime();
    let banned = document.getElementById("updateBanned" + id).value;
    body.banned = banned !== "active";
    body.experience = document.getElementById("updateExperience" + id).value;

    post(root + "/rest/players/" + id, JSON.stringify(body));
    loadContent(root, "", 1);
}

function clickCreate() {
    let elem = document.getElementById("createButton");
    if (elem.style.display === "none") {
        elem.style.display = "block";
    } else {
        elem.style.display = "none"
    }
}

function processCreate(root) {
    let body = {};
    body.name = document.getElementById("inputNameNew").value;
    body.title = document.getElementById("inputTitleNew").value;
    body.race = document.getElementById("inputRaceNew").value.toUpperCase();
    body.profession = document.getElementById("inputProfessionNew").value.toUpperCase();
    body.birthday = new Date(document.getElementById("inputBirthdayNew").value).getTime();
    if (document.getElementById("inlineRadioNew1").checked) {
        body.banned = false;
    } else if (document.getElementById("inlineRadioNew2").checked) {
        body.banned = true;
    }
    body.experience = document.getElementById("inputExperienceNew").value;

    let response = post(root + "/rest/players/", JSON.stringify(body));
    if (response.status === 200) {
        document.getElementById("inputNameNew").value = "";
        document.getElementById("inputTitleNew").value = "";
        document.getElementById("inputRaceNew").value = "Human";
        document.getElementById("inputProfessionNew").value = "Warrior";
        document.getElementById("inputBirthdayNew").value = "";
        if (document.getElementById("inlineRadioNew2").checked) {
            document.getElementById("inlineRadioNew2").checked = false;
            document.getElementById("inlineRadioNew1").checked = true;
        }
        document.getElementById("inputExperienceNew").value = "";

    }

    processSearch(root, 1);
}

function processDelete(root, id) {
    Delete(root + "/rest/players/" + id);
    processSearch(root, 1);
}

    
        
     