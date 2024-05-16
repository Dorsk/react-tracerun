<!DOCTYPE Tree SYSTEM "IAWDiagnostic.dtd">
<Tree chk="-1838471464" type="Tree" onEnd="default" onBackExit="back">
	<Header>
		<IFile>
			<FileName name="menu_navigation_datas">
			</FileName>
			<Modif date="26/04/2023" site="Actia" user="dbarrieu">
			</Modif>
		</IFile>
		<FrontPage title="Muliple screens" description="Navigation between screens and datas">
			</FrontPage>
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
		<DiagScreen posc="3" posl="3" destc="5" destl="5" ident="12">
			<ReadECUDataScr ScreenName="">
				<NamedBuffer name="VIN">
				</NamedBuffer>
			</ReadECUDataScr>
			<Navigation id="1" toId="2">
			</Navigation>
		</DiagScreen>
		<Act posc="5" posl="5" destc="6" destl="6" ident="10">
			<ExecSODVAPI Request="/components/abs/data/vin2" HttpMethod="GET">
				<Comment>GET VIN2
				</Comment>
				<NamedBuffer name="VIN2">
				</NamedBuffer>
			</ExecSODVAPI>
		</Act>
		<DiagScreen posc="6" posl="6" destc="7" destl="7" ident="52">
			<ReadECUDataScr ScreenName="">
				<NamedBuffer name="VIN2">
				</NamedBuffer>
			</ReadECUDataScr>
			<Navigation id="2" backToId="1" toId="3">
			</Navigation>
		</DiagScreen>
		<Act posc="7" posl="7" destc="8" destl="8" ident="10">
			<ExecSODVAPI Request="/components/abs/data/vin3" HttpMethod="GET">
				<Comment>GET VIN3
				</Comment>
				<NamedBuffer name="VIN3">
				</NamedBuffer>
			</ExecSODVAPI>
		</Act>
		<DiagScreen posc="8" posl="8" destc="9" destl="9" ident="142">
			<ReadECUDataScr ScreenName="">
				<NamedBuffer name="VIN3">
				</NamedBuffer>
			</ReadECUDataScr>
			<Navigation id="3" backToId="2">
			</Navigation>
		</DiagScreen>
		<End posc="5" posl="5" ident="13">
		</End>
	</cel>
</Tree>
