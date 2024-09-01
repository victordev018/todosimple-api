// url da nossa api na rota de buscar todas as tasks do user de id 1
const url = "http://localhost:8080/task/user/1";

// função para ocultar o loader quando a api funcionar
function hideLoader(){
    document.getElementById("loading").style.display = "none";
}

// função para preencher a tabela de tasks
function show(tasks){

    // define as colunas
    let tab = `<thead>
            <th scope="col">#</th>
            <th scope="col">Description</th>
            <th scope="col">Username</th>
            <th scope="col">User id</th>
        </thead>`;

    // define os valores para cada linha pegando de cada uma das tasks da lista de tasks que veio como parâmetro
    for (let task of tasks) {
        tab += `
            <tr>
                <td scope="row">${task.id}</td>
                <td>${task.description}</td>
                <td>${task.user.username}</td>
                <td>${task.user.id}</td>
            </tr>
        `;
    }

    // escreve todos os dados coletados e escritos na variável tab e adiciona html no campo que tem id=tasks
    document.getElementById("tasks").innerHTML = tab;
}

// função não instantânea, função assíncrona, carregada depois da página carregar, acessada a qualquer momento
// terá tempo para se comunicar com a api

// função para se comunicar com a api
async function getAPI(url){
    const response = await fetch(url, {method: "GET"});

    var data = await  response.json();
    console.log(data);

    if (response)
        hideLoader();
    show(data);
}

getAPI(url);