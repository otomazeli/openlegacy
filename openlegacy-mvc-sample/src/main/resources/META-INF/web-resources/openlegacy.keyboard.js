function sendPF(keyCode){
	terminalSession.doAction("[pf" + keyCode - 111 + "]");
} 
dojo.connect(dojo.doc.body, "onkeydown", function(e) {

	var handled = true;
	switch (e.keyCode) {
		case dojo.keys.ENTER:
			terminalSession.doAction("[enter]");
		case dojo.keys.F1:
		case dojo.keys.F2:
		case dojo.keys.F3:
		case dojo.keys.F4:
		case dojo.keys.F5:
		case dojo.keys.F6:
		case dojo.keys.F7:
		case dojo.keys.F8:
		case dojo.keys.F9:
		case dojo.keys.F10:
		case dojo.keys.F11:
		case dojo.keys.F12:
			sendPF(e.keyCode);
		default:
			handled = false;

	}
	if (handled){
		dojo.stopEvent(e);
	}
});