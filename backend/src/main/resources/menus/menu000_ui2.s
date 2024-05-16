<!DOCTYPE Tree SYSTEM "IPSA.dtd">
<Tree chk="-1754633927" type="Tree" onEnd="default" onBackExit="back">
<Header>
<IFile>
<FileName name="menu000_ui2">
</FileName>
<Modif date="12/06/2023" site="Actia" user="jmoura">
</Modif>
</IFile>
<ITool exedate="12/06/2023" name="fr.actia.flowchart.editor.model.engineID" revision="8.8.0.qualifier">
</ITool>
</Header>
<precel>
</precel>
<cel>
<Start posc="1" posl="5" destc="4" destl="5" ident="10">
<Comment>$Rev: $
</Comment>
<Comment>
</Comment>
</Start>
<MenuScreen posc="4" posl="5" ident="11">
    <MenuItem ident="23">
        <LabelContent>
            <Text>Read VIN
            </Text>
        </LabelContent>
        <TargetCell destc="6" destl="1">
        </TargetCell>
    </MenuItem>
<MenuItem ident="24">
<LabelContent>
<Text>Read defaults
</Text>
</LabelContent>
<TargetCell destc="6" destl="4">
</TargetCell>
</MenuItem>
<MenuItem ident="25">
<LabelContent>
<Text>Check VIN
</Text>
</LabelContent>
<TargetCell destc="6" destl="7">
</TargetCell>
</MenuItem>
<MenuItem ident="22">
<LabelContent>
<Text>Guided procedure
</Text>
</LabelContent>
<TargetCell destc="6" destl="9">
</TargetCell>
</MenuItem>
</MenuScreen>
<SubTree posc="6" posl="1" destc="9" destl="5" FileName="menu_vin.s" IsCritical="NO" ident="13">
<FilePath path="diagnostic">
</FilePath>
</SubTree>
<SubTree posc="6" posl="4" destc="9" destl="5" FileName="menu_defaults.s" IsCritical="NO" ident="14">
<FilePath path="diagnostic">
</FilePath>
</SubTree>
<SubTree posc="6" posl="7" destc="9" destl="5" FileName="menu_procedure_guidee.s" IsCritical="NO" ident="18">
<FilePath path="diagnostic">
</FilePath>
</SubTree>
<SubTree posc="6" posl="9" destc="9" destl="5" FileName="menu_test_proto.s" IsCritical="NO" ident="19">
<FilePath path="diagnostic">
</FilePath>
</SubTree>
<End posc="9" posl="5" ident="26">
</End>
</cel>
</Tree>
