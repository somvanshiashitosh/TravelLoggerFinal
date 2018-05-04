
function toggle(divId){
	
	 if(document.getElementById(divId+"-content").style.display == 'none') {
        document.getElementById(divId+"-content").style.display = 'block';
        document.getElementById(divId).innerHTML = 'See less';
    }
    else {
        document.getElementById(divId+"-content").style.display = 'none';
        document.getElementById(divId).innerHTML = 'See more';        
    }
}

function getTodaysDate()
{
	var string="";
	var dtm=new Date();
	string=string.concat(dtm.getDate());
	string=string.concat("/",dtm.getMonth()+1);
	string=string.concat("/",dtm.getFullYear());
	return string;
}