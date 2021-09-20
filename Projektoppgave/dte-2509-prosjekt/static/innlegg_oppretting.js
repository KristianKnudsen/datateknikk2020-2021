let antallFelt = 0;
const ALLOWED_EXTENSIONS = ['png', 'jpg', 'jpeg', 'gif', 'bmp']
let uid = 0;
let antallBilder = 0;

const buttons = `
     <a onclick="opp(this.parentNode)">
        <i title="Logged inn som {{current_user.alias}}"
            class="material-icons icon_opprett">arrow_upward</i>
    </a>
    <a onclick="ned(this.parentNode)">
        <i title="Logged inn som {{current_user.alias}}"
            class="material-icons icon_opprett">arrow_downward</i>
    </a>
    <a onclick="fjern(this.parentNode)">
        <i title="Fjern felt"
            class="material-icons slett">cancel</i>
    </a>`;

function leggTilTekstFelt() {
    if ( antallFelt >= 18 ){
        alert("Maks antall felter nådd")
        return;
    }
    const div = document.createElement('div');
    div.className = "tekst";
    div.id = antallFelt.toString()
    div.innerHTML = `
        <label id="l${antallFelt}" for="t${antallFelt}">Tekst: </label>
        <textarea id="t${antallFelt}" maxlength="3000" required="" class="tekst-felt" name="felt-${antallFelt}-tekst"></textarea>
        ` + buttons + `<br>`;
    document.getElementById('inngangsfelt').appendChild(div);
    antallFelt++;
}

function leggTilBildeFelt() {
    if ( antallBilder >= 6 || antallFelt >= 18) {
        alert("Maks antall bilder er nådd");
        return;
    }
    const div = document.createElement('div');
    div.className = "bilde";
    div.id = antallFelt.toString()
    div.innerHTML = `
        <label id="l${antallFelt}" for="b${antallFelt}">Bilde: </label>
        <input id="b${antallFelt}" required="" class="bilde-felt" onchange="filInfo(this); visBilde(${uid});" name="felt-${antallFelt}-bilde" type="file">
        ` + buttons + `<p><img id="bo${uid++}" alt="plassholder"/></p>`  + `<br>`;
    document.getElementById('inngangsfelt').appendChild(div);
    antallBilder++;
    antallFelt++;
}

function leggTilTekstData(tekst) {
    leggTilTekstFelt();
    document.getElementsByName(`felt-${antallFelt-1}-tekst`)[0].value = tekst
}

function leggTilBildeUuid(uuid){
    const div = document.createElement('div');
    div.className = "uuid";
    div.id = antallFelt.toString()
    let src = "./lastNedBilde/" + uuid;
    div.innerHTML = `
        <label id="l${antallFelt}" for="b${antallFelt}">Bilde: </label>
        <input id="b${antallFelt}" name="felt-${antallFelt}-bildeuuid" type="hidden">
        ` + buttons + `<p><img style="width: 100%" id="${uuid}" src="${src}" alt="plassholder"/></p>`  + `<br>`;
    document.getElementById('inngangsfelt').appendChild(div);
    document.getElementsByName(`felt-${antallFelt}-bildeuuid`)[0].value = uuid
    antallBilder++;
    antallFelt++;
}

function dekrementNode(element){
    let newid = parseInt(element.id)-1
    element.id = (newid).toString()

    if ( element.className === "tekst"){
        document.getElementsByName(`felt-${newid+1}-tekst`)[0].name = `felt-${newid}-tekst`
    } else if (element.className === "uuid"){
        document.getElementsByName(`felt-${newid+1}-bildeuuid`)[0].name = `felt-${newid}-bildeuuid`
    } else{
        document.getElementsByName(`felt-${newid+1}-bilde`)[0].name = `felt-${newid}-bilde`
    }
}

function fjern(element){
    let id = parseInt(element.id)
    for (let i = id+1;  i < antallFelt; i++){
        dekrementNode(document.getElementById(i.toString()))
    }
    if ( element.className == "bilde"){
        antallBilder--;
    }
    element.remove();
    antallFelt--;
}

function opp(element) {
    const id = parseInt(element.id);
    let name;
    if (id === 0) {
        return;
    }
    const child = document.getElementById((id - 1).toString());

    element.parentNode.insertBefore(element, child);

    child.id = id.toString()
    element.id = (id - 1).toString()

    if (element.className === "tekst") {
        name = document.getElementsByName(`felt-${id}-tekst`)[0]
    } else if (element.className === "uuid"){
        name = document.getElementsByName(`felt-${id}-bildeuuid`)[0]
    } else{
        name = document.getElementsByName(`felt-${id}-bilde`)[0]
    }

    if ( child.className === "tekst") {
        document.getElementsByName(`felt-${id - 1}-tekst`)[0].name = `felt-${id}-tekst`
    } else if ( child.className === "uuid" ){
        document.getElementsByName(`felt-${id - 1}-bildeuuid`)[0].name = `felt-${id}-bildeuuid`
    } else{
        document.getElementsByName(`felt-${id-1}-bilde`)[0].name = `felt-${id}-bilde`
    }

    if ( element.className === "tekst") {
        name.name = `felt-${id - 1}-tekst`
    } else if ( element.className === "uuid" ){
        name.name = `felt-${id - 1}-bildeuuid`
    } else{
        name.name = `felt-${id-1}-bilde`
    }
}

function ned(element){
    const id = parseInt(element.id);
    if ( id === antallFelt-1){
        return;
    }
    opp(document.getElementById((id+1).toString()))
}

function filInfo(element){
    let s = element.files[0].size;
    let e = element.files[0].type;
    if ( s > 4*1024*1024 ){
        element.value = "";
        alert("FIL FOR STOR!!!!!");
    }
    if (new RegExp(ALLOWED_EXTENSIONS.join("|")).test(e) === false) {
        element.value = "";
        alert("Filtype ikke støttet. Vi støtter bare png, jpg, jpeg, gif og bmp");
    } // https://stackoverflow.com/questions/5582574/how-to-check-if-a-string-contains-text-from-an-array-of-substrings-in-javascript
}

function visBilde(id){
    document.getElementById(`bo${id}`).className = "aaa";
    document.getElementById(`bo${id}`).src = URL.createObjectURL(this.event.target.files[0]);
}

function hide(element){
    element.style.display = "none";
    let id = element.id[6]
    document.getElementById(`tag${id}`).value = "";
}

function vis_tag(){
    for ( var i = 1; i <= 3; i++){
        if ( document.getElementById(`divtag${i}`).style.display == "none" ){
            document.getElementById(`divtag${i}`).style.display = "block";
            return;
        }
    }
    alert("Maks 3 tags")
}
