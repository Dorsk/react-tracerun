<!DOCTYPE Tree SYSTEM "IAWDiagnostic.dtd">
<Tree chk="-1838471464" type="Tree" onEnd="default" onBackExit="back">
	<Header>
		<IFile>
			<FileName name="menu_vin">
			</FileName>
			<Modif date="26/04/2023" site="Actia" user="dbarrieu">
			</Modif>
		</IFile>
		<FrontPage title="Battery change" description="Read Battery.">
		</FrontPage>
		<ITool exedate="21/12/2022" name="fr.actia.flowchart.editor.model.engineID" revision="8.6.0.202212191225">
		</ITool>
	</Header>
	<cel>
		<Start posc="1" posl="1" destc="111" destl="111" ident="1">
		</Start>
		<QsScreen posc="111" posl="111" destyesc="112" destyesl="112" destnoc="22" destnol="22" ident="2">
			<ScreenTitle title="Préparation du véhicule" description="Effectuer l'action avant de passer à la suite">Coupez le contact du véhicule.
			</ScreenTitle>
		</QsScreen>
		<QsScreen posc="112" posl="112" destyesc="113" destyesl="113" destnoc="22" destnol="22" ident="3">
			<ScreenTitle title="Préparation du véhicule" description="Effectuer l'action avant de passer à la suite">Débrancher la batterie.
			</ScreenTitle>
		</QsScreen>
		<QsScreen posc="113" posl="113" destyesc="114" destyesl="114" destnoc="22" destnol="22" ident="4">
			<ScreenTitle title="Préparation du véhicule" description="Effectuer l'action avant de passer à la suite">Remplacer la batterie.
			</ScreenTitle>
		</QsScreen>
		<QsScreen posc="114" posl="114" destyesc="115" destyesl="115" destnoc="22" destnol="22" ident="5">
			<ScreenTitle title="Préparation du véhicule" description="Effectuer l'action avant de passer à la suite">Lire le code BK sur l'étiquette de la batterie de servitude.
			</ScreenTitle>
			<Saisie>
				<Variable name="nbchiffreCodeBK" screenText="Combien de chiffres contient le code barre ?"/>
			</Saisie>
		</QsScreen>
		<QsScreen posc="115" posl="115" destyesc="224" destyesl="224" destnoc="22" destnol="22" ident="6">
			<ScreenTitle title="Préparation du véhicule" description="Effectuer l'action avant de passer à la suite">Lire le code BK sur l'étiquette de la batterie de servitude.
			</ScreenTitle>
			<Saisie>
				<Variable name="newCodeBK" screenText="Saississez le code BK :"/>
			</Saisie>
		</QsScreen>
		<Act posc="224" posl="224" destc="116" destl="116" ident="19">
			<ExecSODVAPI Request="/components/bceb/data/codeBK" HttpMethod="PUT">
				<Comment>Post codeBK
				</Comment>
				<NamedBuffer name="newCodeBK">
				</NamedBuffer>
			</ExecSODVAPI>
		</Act>
		<QsScreen posc="116" posl="116" destyesc="117" destyesl="117" destnoc="22" destnol="22" ident="7">
			<ScreenTitle title="Préparation du véhicule" description="Effectuer l'action avant de passer à la suite">Télécodage du code BK en cours...
			</ScreenTitle>
		</QsScreen>
		<QsScreen posc="117" posl="117" destyesc="2" destyesl="2" destnoc="22" destnol="22" ident="8">
			<ScreenTitle title="Préparation du véhicule" description="Effectuer l'action avant de passer à la suite">Remplacement de la batterie de servitude effectuée.
			</ScreenTitle>
		</QsScreen>
		<Act posc="2" posl="2" destc="3" destl="3" ident="9">
			<ExecSODVAPI Request="/components/bceb/data/fournisseur" HttpMethod="GET">
				<Comment>GET Fournisseur
				</Comment>
				<NamedBuffer name="Fournisseur">
				</NamedBuffer>
			</ExecSODVAPI>
		</Act>
		<Act posc="3" posl="3" destc="4" destl="4" ident="10">
			<ExecSODVAPI Request="/components/bceb/data/moteurEV" HttpMethod="GET">
				<Comment>GET moteurEV
				</Comment>
				<NamedBuffer name="moteurEV">
				</NamedBuffer>
			</ExecSODVAPI>
		</Act>
		<Act posc="4" posl="4" destc="5" destl="5" ident="11">
			<ExecSODVAPI Request="/components/bceb/data/techBatterie" HttpMethod="GET">
				<Comment>GET techBatterie
				</Comment>
				<NamedBuffer name="techBatterie">
				</NamedBuffer>
			</ExecSODVAPI>
		</Act>
		<Act posc="5" posl="5" destc="6" destl="6" ident="12">
			<ExecSODVAPI Request="/components/bceb/data/typeBetterieConnect" HttpMethod="GET">
				<Comment>GET typeBetterieConnect
				</Comment>
				<NamedBuffer name="typeBetterieConnect">
				</NamedBuffer>
			</ExecSODVAPI>
		</Act>
		<Act posc="6" posl="6" destc="7" destl="7" ident="13">
			<ExecSODVAPI Request="/components/bceb/data/stateBatterie" HttpMethod="GET">
				<Comment>GET stateBatterie
				</Comment>
				<NamedBuffer name="stateBatterie">
				</NamedBuffer>
			</ExecSODVAPI>
		</Act>
		<Act posc="7" posl="7" destc="8" destl="8" ident="14">
			<ExecSODVAPI Request="/components/bceb/data/precisionEtatCharge" HttpMethod="GET">
				<Comment>GET precisionEtatCharge
				</Comment>
				<NamedBuffer name="precisionEtatCharge">
				</NamedBuffer>
			</ExecSODVAPI>
		</Act>
		<Act posc="8" posl="8" destc="9" destl="9" ident="15">
			<ExecSODVAPI Request="/components/bceb/data/tempBatterie" HttpMethod="GET">
				<Comment>GET tempBatterie
				</Comment>
				<NamedBuffer name="tempBatterie">
				</NamedBuffer>
			</ExecSODVAPI>
		</Act>
		<Act posc="9" posl="9" destc="10" destl="10" ident="16">
			<ExecSODVAPI Request="/components/bceb/data/tensionBatterie" HttpMethod="GET">
				<Comment>GET tensionBatterie
				</Comment>
				<NamedBuffer name="tensionBatterie">
				</NamedBuffer>
			</ExecSODVAPI>
		</Act>
		<Act posc="10" posl="10" destc="11" destl="11" ident="17">
			<ExecSODVAPI Request="/components/bceb/data/courantVeilleMini" HttpMethod="GET">
				<Comment>GET courantVeilleMini
				</Comment>
				<NamedBuffer name="courantVeilleMini">
				</NamedBuffer>
			</ExecSODVAPI>
		</Act>
		<Act posc="11" posl="11" destc="12" destl="12" ident="18">
			<ExecSODVAPI Request="/components/bceb/data/compteurChargeDecharge" HttpMethod="GET">
				<Comment>GET compteurChargeDecharge
				</Comment>
				<NamedBuffer name="compteurChargeDecharge">
				</NamedBuffer>
			</ExecSODVAPI>
		</Act>
		<Act posc="12" posl="12" destc="13" destl="13" ident="19">
			<ExecSODVAPI Request="/components/bceb/data/codeBK" HttpMethod="GET">
				<Comment>GET codeBK
				</Comment>
				<NamedBuffer name="codeBK">
				</NamedBuffer>
			</ExecSODVAPI>
		</Act>
		<DiagScreen posc="13" posl="13" destc="14" destl="14" ident="20">
			<ReadECUDataScr ScreenName="Nom du fournisseur de la batterie">
				<NamedBuffer name="Fournisseur" ScreenName="Nom du fournisseur de la batterie"/>
				<NamedBuffer name="moteurEV" ScreenName="Type de moteur"/>
				<NamedBuffer name="techBatterie" ScreenName="Type de technologie de la batterie"/>
				<NamedBuffer name="typeBetterieConnect" ScreenName="Type de batterie connectée"/>
				<NamedBuffer name="stateBatterie" ScreenName="Etat de charge batterie"/>
				<NamedBuffer name="precisionEtatCharge" ScreenName="Précision de la valeur de l'etat de charge batterie"/>
				<NamedBuffer name="tempBatterie" ScreenName="Température batterie"/>
				<NamedBuffer name="tensionBatterie" ScreenName="Tension batterie"/>
				<NamedBuffer name="courantVeilleMini" ScreenName="Courant de veille minimum"/>
				<NamedBuffer name="compteurChargeDecharge" ScreenName="Compteur de charge et de décharge de la batterie de servitude"/>
				<NamedBuffer name="codeBK" ScreenName="Code BK"/>
			</ReadECUDataScr>
			<Navigation id="1" >
			</Navigation>
		</DiagScreen>
		<End posc="14" posl="14" ident="333">
		</End>
	</cel>
</Tree>
