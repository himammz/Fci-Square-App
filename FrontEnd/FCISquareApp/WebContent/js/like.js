function check(form) {
		var like = document.getElementById("like").value;
		
		if (like == "Like") {
			document.getElementById("like").value = "Unlike";
		
			document.getElementById("a").InnerHTML = document
					.getElementById("it").value;
			var tmp = document.getElementById("a").InnerHTML;
			tmp = tmp + 1;
			document.getElementById("a").InnerHTML = tmp;
			form.action = "Like";
			form.method = "POST";
		} else {
			document.getElementById("like").value = "Like";
			document.getElementById("a").InnerHTML = document
					.getElementById("it").value;
			var tmp = document.getElementById("a").InnerHTML;
			tmp = tmp - 1;
			document.getElementById("a").InnerHTML = tmp;
			form.action = "Like";
			form.method = "POST";
		}
}