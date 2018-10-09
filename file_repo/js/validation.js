function verifyLoginData() {
	var email = document.getElementById("user_login_name").value.trim();
	var password = document.getElementById("user_login_pass").value.trim();

	var errorsList = new Array();


	if(isValidEmail() && isValidPassword()) {
		resetErrors();
	} else {
		defineEmailViolations();
		displayViolations();
	}

	function isValidEmail() {
		var regexpName = /(^[a-zA-Z][a-zA-Z\.\-_0-9]{3,19}@([a-zA-Z]{2,5}\.){1,2}[a-zA-Z]{2,5}$)|(^[а-яА-Я][а-яА-Я\.\-_0-9]{3,19}@([а-яА-Я]{2,5}\.){1,2}[а-яА-Я]{2,5}$)/;
		return regexpName.test(email.trim());
	}

	function isValidPassword() {
		return passwordViolations == 0;
	}

	function defineEmailViolations() {
		var counter = 0;

		if(email.length == 0) {
			errorsList[counter++] = "login must not be empty";
			return;
		}
		if(isNotValidEmailStructure()) {
			errorsList[counter++] = "login must be valid email";
		}
		if(isLangMixed()) {
			errorsList[counter++] = "email must not contain both latin and cyrillic symbols";
		}
		if(isNotStartsFromLetter()) {
			errorsList[counter++] = "email must begin from letter";
		}
		if(isNotValidNameLength()) {
			errorsList[counter++] = "email name length must be within 4 to 20 symbols";
		}
		if(isNotValidDomainLength()) {
			errorsList[counter++] = "domain name length must be within 2 to 5 symbols";
		}
		if(isOnlyLettersInDomainName()) {
			errorsList[counter++] = "only letters allowed in domain name";
		}
		if(isSpecialSymlolsInEmailName() || isSpecialSymlolsInDomainName()) {
			errorsList[counter++] = "no special symbols allowed except for dot, hyphen, underscore";
		}



		if(isNotValidPasswordLength()) {
			errorsList[counter++] = "password length must be within 8 to 20 symbols";
		}
		if(isNotContainsLowerCase()) {
			errorsList[counter++] = "password should contain at least one lower-case letter.";
		}
		if(isNotContainsUpperCase()) {
			errorsList[counter++] = "password should contain at least one upper-case letter.";
		}
		if(isNotContainsDigit()) {
			errorsList[counter++] = "password should contain at least one digit.";
		}
		if(isNotContainsSymbol()) {
			errorsList[counter++] = "password should contain at least one symbol: '!', '%', '&', '?', '@'.";
		}

		// if(errorsList.length == 0) {
		// 	errorsList[counter++] = "undefined email error";
		// }
	}

	function isNotValidEmailStructure() {
		var arr = email.match(/@/g);
		return email.search(/.+@.*[.].{2,5}/) == -1 || arr.length > 1;
	}

	function isLangMixed() {
		return /[a-zA-Z]/.test(email) && /[а-яА-Я]/.test(email);
	}

	function isNotStartsFromLetter() {
		return /^[^a-zA-Zа-яА-Я]/.test(email);
	}

	function isNotValidNameLength() {
		var name = email.split("@")[0];
		return name.length < 4 || name.length > 20; 
	}

	function isNotValidDomainLength() {
		var subdomains =  email.split("@")[1].split(".");
		for(let i = 0; i < subdomains.length; i++) {
			if(subdomains[i].length < 2 || subdomains[i].length > 5) 
				return 1;
		}
		return 0; 
	}

	function isOnlyLettersInDomainName() {
		var domain =  email.split("@")[1];
		
		return /[^a-zA-Zа-яА-Я\.]/.test(domain) 
	}

	function isSpecialSymlolsInEmailName() {		
		return /[^a-zA-Zа-яА-Я\.\-_0-9]/.test(email.split("@")[0]);
	}

	function isSpecialSymlolsInDomainName() {		
		return /[^a-zA-Zа-яА-Я\.\-_0-9]/.test(email.split("@")[1]);
	}


	function passwordViolations() {
		var violations = 0;

		if(isNotValidPasswordLength()) {
			violations++;
		}
		if(isNotContainsLowerCase()) {
			violations++;
		}
		if(isNotContainsUpperCase()) {
			violations++;
		}
		if(isNotContainsDigit()) {
			violations++;
		}
		if(isNotContainsSymbol()) {
			violations++;
		}
		return violations == 0;
	}

	function isNotValidPasswordLength() {
		return password.length < 8 || password.length > 20;
	}

	function isNotContainsLowerCase() {
		return !/[a-z]/.test(password);
	} 

	function isNotContainsUpperCase() {
		return !/[A-Z]/.test(password);
	} 

	function isNotContainsDigit() {
		return !/[0-9]/.test(password);
	} 

	function isNotContainsSymbol() {
		return !/[!%&?@]/.test(password);
	}
	

	function displayViolations() {
		if(errorsList.length > 0) {
			resetErrors();
			var ul = document.getElementById("error_list");
			for (f of errorsList) {
				let li = document.createElement("li");
				li.appendChild(document.createTextNode(f));
				ul.appendChild(li);
			}
		}
	}

	function resetErrors() {
		var ul = document.getElementById("error_list");
		ul.innerHTML = "";
	}
}

/*
		name restrictions

		domain restrictions

	third-level domains not allowed

		
		password restrictions

	too short password
	too long password
	should contain lower-case letters
	should contain upper-case letters
	should contain digits
	should contain special characters (at least one): ! % & ?

*/



