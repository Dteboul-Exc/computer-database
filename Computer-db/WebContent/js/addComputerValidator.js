
function onLoad() {
	var x = document.getElementById('error');
	x.style.visibility = 'hidden';
}
function myFunction() {
  var x, text;

  // Get the value of the input field with id="numb"
  x = document.getElementById("computerName").value;

  if (x.value === "") {
    text = "Input not valid";
    event.preventDefault()
  } else {
    text = "Input OK";
  }
  document.getElementById("demo").innerHTML = text;
}
