; 脚本由 Inno Setup 脚本向导 生成！
; 有关创建 Inno Setup 脚本文件的详细资料请查阅帮助文档！

#define MyAppName "CosmoRun"
#define MyAppNameCN "宇宙漫步"
#define MyAppVersion "2.1.1.0"
#define MyAppPublisher "Werelone"
#define MyAppExeName "CosmoRun.exe"

[Setup]
; 注: AppId的值为单独标识该应用程序。
; 不要为其他安装程序使用相同的AppId值。
; (生成新的GUID，点击 工具|在IDE中生成GUID。)
AppId={{D12C0A83-3264-418C-8495-23EC03B783BC}
AppName={#MyAppName}
AppVersion={#MyAppVersion}AppVerName={#MyAppName} {#MyAppVersion}
AppPublisher={#MyAppPublisher}
AppPublisherURL=http://www.werelone.cn/
DefaultDirName={pf}\{#MyAppName}
AppendDefaultDirName=yes
UninstallDisplayName={#MyAppName}
DisableProgramGroupPage=yes
OutputDir=E:\ProgramWorld\Project\CosmoRun\install\安装包
OutputBaseFilename=CosmoRun_2.1_安装包
;安装包图标
SetupIconFile=E:\ProgramWorld\Project\CosmoRun\install\图标\CosmoRun-setup-icon.ico
;卸载图标
UninstallDisplayIcon=E:\ProgramWorld\Project\CosmoRun\install\图标\CosmoRun-setup-icon.ico
SolidCompression=yes
;安装过程不允许取消
AllowCancelDuringInstall=no
;允许输入UNC路径
AllowUNCPath=no
;可卸载
Uninstallable=yes

[Languages]
Name: "chinesesimp"; MessagesFile: "compiler:Default.isl"

[CustomMessages]
AdditionalIcons=附加快捷方式:
CreateDesktopIcon=创建桌面快捷方式(&D)
ProgramsIcon=添加快捷方式到开始菜单

[Tasks]
Name: "desktopicon"; Description: "{cm:CreateDesktopIcon}"; GroupDescription: "{cm:AdditionalIcons}"
Name: programsicon; Description: "{cm:ProgramsIcon}"; GroupDescription: "{cm:AdditionalIcons}"

[Files]
Source: "E:\ProgramWorld\Project\CosmoRun\app\CosmoRun x86\CosmoRun.exe"; DestDir: "{app}"; Flags: ignoreversion
Source: "E:\ProgramWorld\Project\CosmoRun\app\CosmoRun x86\app.jar"; DestDir: "{app}"; Flags: ignoreversion
Source: "E:\ProgramWorld\Project\CosmoRun\app\CosmoRun x86\bin\*"; DestDir: "{app}\bin"; Flags: ignoreversion recursesubdirs createallsubdirs
Source: "E:\ProgramWorld\Project\CosmoRun\app\CosmoRun x86\lib\*"; DestDir: "{app}\lib"; Flags: ignoreversion recursesubdirs createallsubdirs
Source: "E:\ProgramWorld\Project\CosmoRun\app\CosmoRun x86\Readme.txt"; DestDir: "{app}"; Flags: ignoreversion
; 注意: 不要在任何共享系统文件上使用“Flags: ignoreversion”

[Icons]
Name: "{commonprograms}\CosmoRun\{#MyAppNameCN}"; Filename: "{app}\{#MyAppExeName}"; Tasks: programsicon
Name: "{commonprograms}\CosmoRun\Uninstall"; Filename: "{uninstallexe}"; Tasks: programsicon
Name: "{commondesktop}\{#MyAppNameCN}"; Filename: "{app}\{#MyAppExeName}"; Tasks: desktopicon
[Run]Filename: "{app}\{#MyAppExeName}"; Description: "{cm:LaunchProgram,{#StringChange(MyAppNameCN, '&', '&&')}}"; Flags: nowait postinstall skipifsilent

[UninstallDelete]
Type: files; Name: "{app}\bin\data"Type: dirifempty; Name: "{app}"
