<?php
// define variables and set to empty value
require_once '../reks/addLog.php';
$alt = $ust = $n  = $sirali = "";
?>
<html>
	<head>
	<meta name="viewport" content="width=device-width, initial-scale=1.0">
	<title>berkenin.com: Yap bir kura</title>
		<style>
table,tr {
  border: 1px solid black;
}
</style>
	</head>
	<body>
	<h2 style="color:blue;">Rastgele Sayı Seçici</h2>
	<hr/>
		<form name="form1" action="kura.php" method="post">
			<table>
				<thead>herkese alt-üst arası numara ver, birkaç tanesini seç!</thead>
				<tr>
					<td>alt:</td>
					<td>
						<input type="text" name="alt" style="width:50;"/>
					</td>
					<td>ust:</td>
					<td>
						<input type="text" name="ust" style="width:50;"/>
					</td>
					<td>adet:</td>
					<td>
						<input type="text" name="n" style="width:50;"/>
					</td>
				</tr>
				<tr>
					<td colspan="6">
						<input type="submit" name="rastgele1" value="Rastgele" style="width:100%;"/>
					</td>
				</tr>
				<tr>
					<td>
						<input type="submit" name="tavla" value="Tavla"/>
					</td>
					<td>
						<input type="submit" name="tombala" value="Tombala"/>
					</td>
					<td>
						<input type="checkbox" name="chkSirali" value="1">
						<label for="chkSirali"> Sıralı </label>
					</td>
					
					
				</tr>
			</table>
		</form>

<?php
function test_input($data) {
  $data = trim($data);
  $data = stripslashes($data);
  $data = htmlspecialchars($data);
  return $data;
};
?>
<?php
if ($_SERVER["REQUEST_METHOD"] == "POST") {
	$sayiArray = $secilen = [];
	$alt	 = test_input($_POST["alt"]		 );
	$ust	 = test_input($_POST["ust"]		 );
	$n 		 = test_input($_POST["n"]		 ); 
	$sirali  = test_input($_POST["chkSirali"]);
};
	
function arrayDoldur($alt,$ust){
	$secilecekler = $ust - $alt + 1; 
	for ($i = 0; $i < $secilecekler; $i++){
	  $sayiArray[]=intval($i+$alt);
	};

	return $sayiArray; 
};

function attir($sayiAdedi,$alt,$ust,$arr){
	$secilen = [];
	for ($j = 0; $j < $sayiAdedi; $j++) {
        //randomize
        $elemanSayisi = sizeof($arr);
		
        $secilenIndis = rand(0,$elemanSayisi - 1); // rand ikisi inclusive dikkat!		
        $secilen[]=$arr[$secilenIndis];

		array_splice($arr,$secilenIndis,1);
    }
	
	if(isset($_POST['chkSirali']))
	{
	     sort($secilen);
	};
   
	return $secilen;
};



function rastgele($alt,$ust,$n){ 

	if(isset($_POST['alt']) && isset($_POST['ust']) && isset($_POST['n'])){
		if ($ust < $alt) {
		echo "<br/>üst limit alt limitten küçük olamaz";
		return;
		};
		if ($n < 1) {
		echo "<br/>atılacak sayı adedi az";
		return;
		};
		if ($n > ($ust - $alt + 1)) {
		echo "<br/>atılacak sayı adedi fazla";
		return;
		};
	};
	
	$arr = arrayDoldur($alt, $ust);
    $rast = attir($n, $alt, $ust,$arr);
	
	echo "<br/> secilen:";
	echo "<table style='width:400px'><tr><td>";
	foreach ($rast as $deg){ echo $deg." ";}
	echo "</td></tr></table>";
	
};

if(isset($_POST['rastgele1']))
{
	rastgele($alt,$ust,$n);	
		addLog(strval('rastgele'), 0);
};
if(isset($_POST['tavla']))
{
	rastgele(1,6,1);
	rastgele(1,6,1);
	addLog(strval('tavla'), 0);

};
if(isset($_POST['tombala']))
{
	rastgele(1,90,90);
	addLog(strval('tombala'), 0);

};
?>
	</body>
</html>