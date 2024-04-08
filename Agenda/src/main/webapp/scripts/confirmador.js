/**
 * Confirmação de exclusão de um contato
 * @author Aluno Lucas
 */
function confirmar(idcon) {
	let resposta = confirm("Deseja realmente excluir este contato")
	if (resposta === true) {
		window.location.href = "delete?idcon=" +idcon
	}
}