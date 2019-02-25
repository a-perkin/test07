//показать / скрыть материалы
const HOST = 'http://localhost:8080';

let materials = document.getElementById("materials");

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

let request = new XMLHttpRequest();
request.open('GET', HOST + '/rest/materials', true);
console.log(request.response);
request.send();
function getMaterials() {
    let aoMaterials = JSON.parse(request.response);
    let listMaterials = document.getElementById("listMaterials");



    let sMaterial;
    let text;
    for (let i = 0; i < aoMaterials.length; i++) {
        if (listMaterials.childNodes.length <= aoMaterials.length){
            sMaterial = document.createElement("li");
            text = 'Название: ' + aoMaterials[i].name + ' | Толщина: ' + aoMaterials[i].thickness + '| Цена: ' + aoMaterials[i].price;
            sMaterial.innerHTML = text;
            listMaterials.appendChild(sMaterial);
            console.log(sMaterial);
        }
    }

}

