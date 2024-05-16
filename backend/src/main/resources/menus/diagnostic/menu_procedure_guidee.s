<!DOCTYPE Tree SYSTEM "IAWDiagnostic.dtd">
<Tree chk="-1838471464" type="Tree" onEnd="default" onBackExit="back">
	<Header>
		<IFile>
			<FileName name="menu_procedure_guidee">
			</FileName>
			<Modif date="26/04/2023" site="Actia" user="dbarrieu">
			</Modif>
		</IFile>
		<FrontPage title="Guided process" description="Perform the procedures displayed on the screen before proceeding to the next step">
			</FrontPage>
		<ITool exedate="21/12/2022" name="fr.actia.flowchart.editor.model.engineID" revision="8.6.0.202212191225">
		</ITool>
	</Header>
	<cel>
		<Start posc="1" posl="1" destc="2" destl="2" ident="11">
		</Start>
		<QsScreen posc="2" posl="2" destyesc="3" destyesl="3" destnoc="22" destnol="22" ident="12">
			<ScreenTitle title="Préparation du véhicule" description="Effectuer l'action avant de passer à la suite">Coupez le contact du véhicule
			</ScreenTitle>
		</QsScreen>
		<QsScreen posc="3" posl="3" destyesc="4" destyesl="4" destnoc="22" destnol="22" ident="13">
			<ScreenTitle title="Préparation du véhicule" description="Effectuer l'action avant de passer à la suite">Débrancher la batterie et attendre 5 minutes.
			</ScreenTitle>
		</QsScreen>
		<QsScreen posc="4" posl="4" destyesc="5" destyesl="5" destnoc="22" destnol="22" ident="14">
			<ScreenTitle title="Préparation du véhicule" description="Effectuer l'action avant de passer à la suite">Rebrancher la batterie
			</ScreenTitle>
		</QsScreen>
		<Act posc="5" posl="5" destc="6" destl="6" ident="10">
			<ExecSODVAPI Request="/components/abs/data/vin" HttpMethod="GET">
				<Comment>GET VIN
				</Comment>
				<NamedBuffer name="VIN">
				</NamedBuffer>
			</ExecSODVAPI>
		</Act>
		<QsScreen posc="6" posl="6" destyesc="8" destyesl="8" destnoc="22" destnol="22" ident="15">
			<ScreenTitle title="Préparation du véhicule" description="Effectuer l'action avant de passer à la suite">Le VIN du véhicule correspond-t-il a celui de la carte crise ?
			</ScreenTitle>
			<NamedBuffer name="VIN">
			</NamedBuffer>
		</QsScreen>
		<QsScreen posc="8" posl="8" destyesc="22" destyesl="22" destnoc="22" destnol="22" ident="16">
			<ScreenTitle title="Préparation du véhicule" description="Effectuer l'action avant de passer à la suite">Retourner à l'écran d'accueil ?
			</ScreenTitle>
		</QsScreen>
		<End posc="22" posl="22" ident="22">
		</End>
	</cel>
</Tree>
