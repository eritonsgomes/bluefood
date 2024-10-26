const NUMERIC_CHAR_CODES = getNumericCharCodes();
const CONTROL_CHAR_CODES = getControlCharCodes();

function redirectTo(url) {
    window.location = url;
}

function getNumericCharCodes() {
    let numericCharCodes = new Map();

    numericCharCodes.set(0, [48, 96]);
    numericCharCodes.set(1, [49, 97]);
    numericCharCodes.set(2, [50, 98]);
    numericCharCodes.set(3, [51, 99]);
    numericCharCodes.set(4, [52, 100]);
    numericCharCodes.set(5, [53, 101]);
    numericCharCodes.set(6, [54, 102]);
    numericCharCodes.set(7, [55, 103]);
    numericCharCodes.set(8, [56, 104]);
    numericCharCodes.set(9, [57, 105]);

    return numericCharCodes;
}

function getControlCharCodes() {
    let isControlCharCode = new Map();

    isControlCharCode.set('BS', [8]);
    isControlCharCode.set('HT', [9]);

    return isControlCharCode;
}

function isNumericCharCode(charCode) {
    let isNumericCharCode = false;

    for (let number = 0; number <= 9; number++) {
        const charCodes = NUMERIC_CHAR_CODES.get(number);
        isNumericCharCode = charCodes.find(n => n === charCode) ? true: false;
        if (isNumericCharCode) break;
    }

    return isNumericCharCode;
}

function isControlCharCode(charCode) {
    let isControlCharCode = false;

    for (const [key, value] of CONTROL_CHAR_CODES.entries()) {
        isControlCharCode = value.find(n => n === charCode) ? true: false;
        if (isControlCharCode) break;
    }

    return isControlCharCode;
}

function isNumberKey(evt) {
    var charCode = (evt.wich) ? evt.wich : evt.keyCode;

    var isCharCodeNumeric = isNumericCharCode(charCode);
    var isCharCodeControl = isControlCharCode(charCode);

    return (isCharCodeNumeric === true || isCharCodeControl === true);
}

function buscaRestaurante(categoriaId) {
	var tipoDeBuscaInput = document.getElementById("tipo-de-busca");
	var textoBuscaInput = document.getElementById("texto-busca");
	var categoriaIdInput = document.getElementById("categoria-id");

	if (categoriaId == null) {
		tipoDeBuscaInput.value = "TEXTO";
        categoriaIdInput.value = null;
	} else {
		tipoDeBuscaInput.value = "CATEGORIA";
		textoBuscaInput.value = null;
		document.getElementById("categoria-id").value = categoriaId;
	}

	document.getElementById("form-busca-restaurante").submit();
}
