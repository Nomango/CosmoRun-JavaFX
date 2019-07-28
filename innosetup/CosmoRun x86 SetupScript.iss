; �ű��� Inno Setup �ű��� ���ɣ�
; �йش��� Inno Setup �ű��ļ�����ϸ��������İ����ĵ���

#define MyAppName "CosmoRun"
#define MyAppNameCN "��������"
#define MyAppVersion "2.1.1.0"
#define MyAppPublisher "Werelone"
#define MyAppExeName "CosmoRun.exe"

[Setup]
; ע: AppId��ֵΪ������ʶ��Ӧ�ó���
; ��ҪΪ������װ����ʹ����ͬ��AppIdֵ��
; (�����µ�GUID����� ����|��IDE������GUID��)
AppId={{D12C0A83-3264-418C-8495-23EC03B783BC}
AppName={#MyAppName}
AppVersion={#MyAppVersion}AppVerName={#MyAppName} {#MyAppVersion}
AppPublisher={#MyAppPublisher}
AppPublisherURL=http://www.werelone.cn/
DefaultDirName={pf}\{#MyAppName}
AppendDefaultDirName=yes
UninstallDisplayName={#MyAppName}
DisableProgramGroupPage=yes
OutputDir=E:\ProgramWorld\Project\CosmoRun\install\��װ��
OutputBaseFilename=CosmoRun_2.1_��װ��
;��װ��ͼ��
SetupIconFile=E:\ProgramWorld\Project\CosmoRun\install\ͼ��\CosmoRun-setup-icon.ico
;ж��ͼ��
UninstallDisplayIcon=E:\ProgramWorld\Project\CosmoRun\install\ͼ��\CosmoRun-setup-icon.ico
SolidCompression=yes
;��װ���̲�����ȡ��
AllowCancelDuringInstall=no
;��������UNC·��
AllowUNCPath=no
;��ж��
Uninstallable=yes

[Languages]
Name: "chinesesimp"; MessagesFile: "compiler:Default.isl"

[CustomMessages]
AdditionalIcons=���ӿ�ݷ�ʽ:
CreateDesktopIcon=���������ݷ�ʽ(&D)
ProgramsIcon=��ӿ�ݷ�ʽ����ʼ�˵�

[Tasks]
Name: "desktopicon"; Description: "{cm:CreateDesktopIcon}"; GroupDescription: "{cm:AdditionalIcons}"
Name: programsicon; Description: "{cm:ProgramsIcon}"; GroupDescription: "{cm:AdditionalIcons}"

[Files]
Source: "E:\ProgramWorld\Project\CosmoRun\app\CosmoRun x86\CosmoRun.exe"; DestDir: "{app}"; Flags: ignoreversion
Source: "E:\ProgramWorld\Project\CosmoRun\app\CosmoRun x86\app.jar"; DestDir: "{app}"; Flags: ignoreversion
Source: "E:\ProgramWorld\Project\CosmoRun\app\CosmoRun x86\bin\*"; DestDir: "{app}\bin"; Flags: ignoreversion recursesubdirs createallsubdirs
Source: "E:\ProgramWorld\Project\CosmoRun\app\CosmoRun x86\lib\*"; DestDir: "{app}\lib"; Flags: ignoreversion recursesubdirs createallsubdirs
Source: "E:\ProgramWorld\Project\CosmoRun\app\CosmoRun x86\Readme.txt"; DestDir: "{app}"; Flags: ignoreversion
; ע��: ��Ҫ���κι���ϵͳ�ļ���ʹ�á�Flags: ignoreversion��

[Icons]
Name: "{commonprograms}\CosmoRun\{#MyAppNameCN}"; Filename: "{app}\{#MyAppExeName}"; Tasks: programsicon
Name: "{commonprograms}\CosmoRun\Uninstall"; Filename: "{uninstallexe}"; Tasks: programsicon
Name: "{commondesktop}\{#MyAppNameCN}"; Filename: "{app}\{#MyAppExeName}"; Tasks: desktopicon
[Run]Filename: "{app}\{#MyAppExeName}"; Description: "{cm:LaunchProgram,{#StringChange(MyAppNameCN, '&', '&&')}}"; Flags: nowait postinstall skipifsilent

[UninstallDelete]
Type: files; Name: "{app}\bin\data"Type: dirifempty; Name: "{app}"
