//показать / скрыть материалы
let materials = document.getElementById("materials");
let menuFireplace = document.getElementById("menuFireplace");
let menuMaterials = document.getElementById("menuMaterials");
let tableMaterials = document.getElementById('tableMaterials');
let aoMaterials = null;
let oMaterial = {};
//Содержание таблицы материалов в хтмл виде
sMaterials = "";

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
// function addMaterialToTable(arr) {
//
//     for (let i = 0; i < arr.length; i++) {
//         obj = arr[i];
//         sMaterials = sMaterials + "<tr id=\"" + obj.id +"\"><td>" + obj.name + "</td><td>" + obj.price + "</td><td>" + obj.thickness + "</td></tr>";
//
//     }
//     tableMaterials.insertAdjacentHTML('beforeend', sMaterials);
// }
//
//получить список всех материалов + засетить в таблицу
menuMaterials.onclick = function () {
    let xhr = new XMLHttpRequest();
    xhr.onreadystatechange = function () {
        if (xhr.status == 200 && xhr.readyState === 4 && aoMaterials === null){ //последняя проверка временно, что бы при каждом клике не дергало рест
            aoMaterials = JSON.parse(xhr.response);
            console.log(aoMaterials);
            for (let i = 0; i < aoMaterials.length; i++) {
                oMaterial = aoMaterials[i];
                addSampleRow(oMaterial);
            }
        }
    };
    xhr.open('GET', '/rest/materials', true);
    xhr.send();
};

function addSampleRow(oMaterial) {
    let table = document.getElementById('tableMaterials');
    let row = table.insertRow(table.rows.length);
    let cellName = row.insertCell(0);
    let cellPrice = row.insertCell(1);
    let cellThickness = row.insertCell(2);
    let cellEdit = row.insertCell(3);
    let cellRemove = row.insertCell(4);

    row.id = 'material_' + oMaterial.id;
    cellName.innerHTML = oMaterial.name;
    cellPrice.innerHTML = oMaterial.price;
    cellThickness.innerHTML = oMaterial.thickness;

    let btnEdit = document.createElement('input');
    btnEdit.setAttribute("type", "button");
    btnEdit.setAttribute("value", "Edit");
    btnEdit.setAttribute("name", "Edit");
    btnEdit.setAttribute("onclick", 'editRowMaterial(this.parentNode.parentNode.id)');
    cellEdit.appendChild(btnEdit);

    let btnRemove = document.createElement('input');
    btnRemove.setAttribute("type", "button");
    btnRemove.setAttribute("value", "Remove");
    btnRemove.setAttribute("name", "Remove");
    btnRemove.setAttribute("onclick", 'deleteRowMaterial(this.parentNode.parentNode.id)');
    cellRemove.appendChild(btnRemove);
}

function deleteRowMaterial(rowId) {
    let nID = rowId.split('_')[1];
    //console.log(nID);
    let xhr = new XMLHttpRequest();
    xhr.open("POST", '/rest/materials/delete?id=' + nID, true);

    let row = document.getElementById(rowId);
    let table = row.parentNode;
    while (table && table.tagName != 'TABLE'){
        table = table.parentNode;
        if(!table){
            return;
        }
        if (confirm("Удалить материал " + row.childNodes[0].innerHTML + "?")){
            table.deleteRow(row.rowIndex);
            xhr.send();
        }
    }
}

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

//Пост запрос на получение всех материалов
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
            alert(JSON.stringify(this.response));
            addSampleRow(this.response);
            document.forms.material.reset();
        }
    };
}

//Редактирование строки таблицы материалов
function editRowMaterial(rowId) {
    //let nID = rowId.split('_')[1];
    //console.log(nID);
    let row = document.getElementById(rowId);
    row.setAttribute("currentLine", true);
    row.style.background = 'yellow';

    let arrTD = row.childNodes;
    for(let i = 0; i < arrTD.length - 2; i++){
        let textArea = document.createElement('input');
        textArea.value = arrTD[i].innerHTML;
        arrTD[i].innerHTML = '';
        arrTD[i].appendChild(textArea);
    }
    saveRowMaterial(arrTD);
}


function saveRowMaterial(arrTD) {
    let target = event.target;
    if (target.nodeName == 'button'){
        if(confirm("Сохранить?")){
            for(let i = 0; i < arrTD.length - 2; i++) {
                arrTD[i].innerHTML = arrTD[i].firstChild.value;
            }
        }
    }
    console.log(target)

}


