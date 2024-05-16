<!DOCTYPE Tree SYSTEM "IAWDiagnostic.dtd">
<Tree chk="-1838471464" type="Tree" onEnd="default" onBackExit="back">
	<Header>
		<IFile>
			<FileName name="Diagnostics"></FileName>
			<Modif date="26/04/2023" user="dbarrieu" site="Actia" >
			</Modif>
		</IFile>
		<ITool exedate="21/12/2022" name="fr.actia.flowchart.editor.model.engineID" revision="8.6.0.202212191225">
		</ITool>
		<FrontPage title="Diagnostic" description="Run diagnostic procedures through the SOVD API">
		</FrontPage>
	</Header>
	<precel/>
	<cel>
		<Start posc="1" posl="1" destc="2" destl="2" ident="11">
		</Start>
		<SubTree posc="2" posl="2" destc="6" destl="6" ident="10" FileName="menu_vin.s">
			<Comment>Read VIN</Comment>
			<FilePath path="diagnostic/menu_vin.s"></FilePath>
		</SubTree>
		<SubTree posc="4" posl="4" destc="6" destl="6" ident="11" FileName="menu_defaults.s">
			<Comment>Get defauts vehicle</Comment>
			<FilePath path="diagnostic/menu_defaults.s"></FilePath>
		</SubTree>
		<SubTree posc="4" posl="4" destc="6" destl="6" ident="12" FileName="menu_navigation_datas.s">
			<Comment>Screens navigation : API multiple call</Comment>
			<FilePath path="diagnostic/menu_navigation_datas.s"></FilePath>
		</SubTree>
		<SubTree posc="5" posl="5" destc="6" destl="6" ident="12" FileName="menu_procedure_guidee.s">
			<Comment>Guided procedure</Comment>
			<FilePath path="diagnostic/menu_procedure_guidee.s"></FilePath>
		</SubTree>
		<SubTree posc="6" posl="6" destc="7" destl="7" ident="12" FileName="menu_test_proto.s">
			<Comment>Change Battery</Comment>
			<FilePath path="diagnostic/menu_test_proto.s"></FilePath>
		</SubTree>
		<End posc="7" posl="7" ident="13">
		</End>
	</cel>
</Tree>
