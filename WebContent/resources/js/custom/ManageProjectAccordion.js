
getId = function() {
      var numbers = arguments[0].substring(11, arguments[0].length - 7);
      document.getElementById("form:projectId").value = document.getElementById("form:panel:" + numbers + ":id").value;
}
