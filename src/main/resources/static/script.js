function editarFilme(id) {
    let novoTitulo = document.getElementById("titulo").value;
    let novaSinopse = document.getElementById("sinopse").value;
    let novoGenero = document.getElementById("genero").value;
    let novoAnoLancamento = document.getElementById("anoLancamento").value;

    // Verifique se todos os campos estão preenchidos
    if (novoTitulo && novaSinopse && novoGenero && novoAnoLancamento) {
        $.ajax({
            url: '/api/filmes/' + id,
            type: 'PUT',
            contentType: 'application/json',
            data: JSON.stringify({
                titulo: novoTitulo,
                sinopse: novaSinopse,
                genero: novoGenero,
                anoLancamento: novoAnoLancamento
            }),
            success: function (result) {
                alert('Filme atualizado com sucesso!');
                window.location.href = '/filmes'; // Redireciona para a lista de filmes após a atualização
            },
            error: function (error) {
                alert('Erro ao atualizar filme: ' + error.responseText);
            }
        });
    } else {
        alert("Por favor, preencha todos os campos.");
    }
}

function deletarFilme(id) {
    if (confirm("Tem certeza que deseja excluir este filme?")) {
        $.ajax({
            url: '/api/filmes/' + id,
            type: 'DELETE',
            success: function (result) {
                alert('Filme removido com sucesso!');
                window.location.href = '/filmes';
            },
            error: function (error) {
                alert('Erro ao remover filme: ' + error.responseText);
            }
        });
    }
}