<!DOCTYPE Tree SYSTEM "IAWDiagnostic.dtd">
<Tree chk="-1838471464" type="Tree" onEnd="default" onBackExit="back">
	<Header>
		<IFile>
			<FileName name="menu000">
			</FileName>
			<Modif date="26/04/2023" site="Actia" user="dbarrieu">
			</Modif>
		</IFile>
		<ITool exedate="21/12/2022" name="fr.actia.flowchart.editor.model.engineID" revision="8.6.0.202212191225">
		</ITool>
	</Header>
	<cel>
		<Start posc="1" posl="1" destc="2" destl="2" ident="11">
		</Start>
		<Act posc="2" posl="2" destc="3" destl="3" ident="10">
			<ExecSODVAPI Request="/components/abs/data/vin" HttpMethod="GET">
				<Comment>GET VIN
				</Comment>
				<NamedBuffer name="VIN">
				</NamedBuffer>
			</ExecSODVAPI>
		</Act>
		<DiagScreen posc="3" posl="3" destc="4" destl="4" ident="12">
			<ReadECUDataScr ScreenName="">
				<NamedBuffer name="VIN">
				</NamedBuffer>
			</ReadECUDataScr>
		</DiagScreen>
		<End posc="4" posl="4" ident="13">
		</End>
	</cel>
</Tree>
