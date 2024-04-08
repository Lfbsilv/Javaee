/**
 * Validação de Formulario
 * @author Aluno Lucas Francisco
 * 
 * 
 */
function validar() {
	//alert("test") teste inicial
	let nome = frmContato.nome.value
	let fone = frmContato.fone.value

	if (nome === "") {
		alert("Preencha o campo nome!")
		frmContato.nome.focus()
		return false
	} else if (fone === "") {
		alert("Prencha o campo fone!")
		frmContato.fone.focus()
		return false
	} else {
		document.forms["frmContato"].submit()
	}
}
