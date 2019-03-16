//показать / скрыть материалы
let materials = document.getElementById("materials");
let menuFireplace = document.getElementById("menuFireplace");
let listMaterials = document.getElementById("listMaterials");
let menuMaterials = document.getElementById("menuMaterials");
let aoMaterials = null;
let oMaterial = {};
//строка в таблице с материалами
let sMaterials = "";
let obj;

let visible = false;
function showMaterials() {
    if(visible){
        materials.style.display = 'none';
        visible = false;
    } else {
        materials.style.display = 'block';
        visible = true;
    }
}

//Добавить материал в таблицу
function addMaterialToTable(arr) {

    for (let i = 0; i < arr.length; i++) {
        obj = arr[i];
        sMaterials = sMaterials + "<tr id=\"" + obj.id +"\"><td>" + obj.name + "</td><td>" + obj.price + "</td><td>" + obj.thickness + "</td></tr>";

    }
    let tableMaterials = document.getElementById('tableMaterials');
    tableMaterials.insertAdjacentHTML('beforeend', sMaterials);
}

//получить список всех материалов
menuMaterials.onclick = function () {
    let xhr = new XMLHttpRequest();
    xhr.onreadystatechange = function () {
        if (xhr.status == 200 && xhr.readyState === 4 && aoMaterials === null){
            aoMaterials = JSON.parse(xhr.response);
            console.log(aoMaterials);
            // for (let i = 0; i < aoMaterials.length; i++) {
            //     if (listMaterials.childNodes.length <= aoMaterials.length){
            //         addMaterialToList(aoMaterials[i].name, aoMaterials[i].thickness, aoMaterials[i].price);
            //     }
            // }
            addMaterialToTable(aoMaterials);
        }
    };
    xhr.open('GET', '/rest/materials', true);
    xhr.send();
};

//Создать новый материал (новую запись в базе)
document.forms.material.onsubmit = function() {
    oMaterial.name = this.elements.sName.value;
    oMaterial.price = parseFloat(this.elements.nPrice.value);
    oMaterial.thickness = parseInt(this.elements.nThickness.value, 10);
    console.log(oMaterial);
    if (oMaterial) {
        addMaterial(oMaterial);
    }
    return false;
};

//Пост запрос
function addMaterial(obj){
    console.log("obj = " + JSON.stringify(obj));
    let xhr = new XMLHttpRequest();
    xhr.responseType = 'json';
    xhr.open("POST", '/rest/materials/upd', true);
    xhr.setRequestHeader('Content-Type', 'application/json');
    xhr.send(JSON.stringify(obj));
    xhr.onload = function () {
        if (this.status == 200) {
            console.log('response', this.response); // JSON response
        }
    };
}

