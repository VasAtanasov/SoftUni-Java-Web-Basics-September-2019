document.addEventListener("DOMContentLoaded", function (event) {
	const JAVA_FUNDAMENTALS = "Java-Fundamentals";
	const JAVA_ADVANCED = "Java-Advanced";
	const JAVA_DB = "Java-DB";
	const JAVA_WEB = "Java-Web";

	const signButton = document.querySelector("#button");
	const costContainer = document.querySelector(
		".courses-container-footer .cost"
	);
	const boxesContainer = document.querySelector(".boxes");

	let courses = {
		"Java-Fundamentals": {
			title: "Java Fundamentals",
			cost: 170,
			value: JAVA_FUNDAMENTALS
		},
		"Java-Advanced": {
			title: "Java Advanced",
			cost: 180,
			value: JAVA_ADVANCED
		},
		"Java-DB": {
			title: "Java DB",
			cost: 190,
			value: JAVA_DB
		},
		"Java-Web": {
			title: "Java Web",
			cost: 490,
			value: JAVA_WEB
		}
	};

	Object.values(courses).forEach((obj, idx) => {
		const chechbox = document.createElement("input");
		chechbox.setAttribute("type", "checkbox");
		chechbox.setAttribute("value", `${obj["value"]}`);
		chechbox.setAttribute("id", `box-${idx}`);

		const lable = document.createElement("label");
		lable.setAttribute("for", `box-${idx}`);
		lable.innerHTML = obj["title"];

		boxesContainer.appendChild(chechbox);
		boxesContainer.appendChild(lable);
	});

	const copyObject = function (src) {
		return Object.assign({}, src);
	}

	signButton.onclick = function (event) {

		let list = document.querySelector(".courses-list .singed-courses");
		let signedCourses = {};

		document
			.querySelectorAll(".courses-list input[type='checkbox']")
			.forEach(el => {
				if (el.checked) {
					signedCourses[el.value] = copyObject(courses[el.value]);
				}
			});

		list.innerHTML = "";

		let titles = Object.keys(signedCourses);

		if (titles.includes(JAVA_FUNDAMENTALS) && titles.includes(JAVA_ADVANCED)) {
			const javaAdvanced = signedCourses["Java-Advanced"];
			javaAdvanced.cost = javaAdvanced.cost - (javaAdvanced.cost * 0.1);
		}

		if (titles.includes(JAVA_FUNDAMENTALS) && titles.includes(JAVA_ADVANCED) && titles.includes(JAVA_DB)) {
			[JAVA_FUNDAMENTALS, JAVA_ADVANCED, JAVA_DB].forEach(title => {
				const course = signedCourses[title];
				course.cost = course.cost - (course.cost * 0.06);
			});
		}

		if (titles.length === 4) {
			signedCourses["HTML-and-CSS"] = {
				title: "HTML and CSS",
				cost: 0,
				value: "HTML-and-CSS"
			}
		}

		let totalCost = 0;

		Object.values(signedCourses).forEach(course => {
			let li = document.createElement("li");
			li.innerHTML = course.value;
			list.appendChild(li);
			totalCost += course.cost;
		});

		let radion = document.querySelector('input[name="radio"]:checked');
		if (radion.value === "online") {
			totalCost = totalCost - (totalCost * 0.06);
		}

		costContainer.innerHTML = `Cost: ${Math.floor(totalCost).toFixed(
			2
		)} BGN`;
	};
});
