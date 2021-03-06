package de.nordgedanken.auto_hotkey

import com.intellij.openapi.editor.colors.TextAttributesKey
import com.intellij.openapi.fileTypes.SyntaxHighlighter
import com.intellij.openapi.options.colors.AttributesDescriptor
import com.intellij.openapi.options.colors.ColorDescriptor
import com.intellij.openapi.options.colors.ColorSettingsPage
import de.nordgedanken.auto_hotkey.colors.AHKColor
import javax.swing.Icon

class AHKColorSettingsPage : ColorSettingsPage {
    private val ATTRS = AHKColor.values().map { it.attributesDescriptor }.toTypedArray()

    private val ANNOTATOR_TAGS = AHKColor.values().associateBy({ it.name }, { it.textAttributesKey })

    override fun getIcon(): Icon = AHKIcons.FILE

    override fun getHighlighter(): SyntaxHighlighter = AHKSyntaxHighlighter()

    override fun getDemoText(): String {
        return """
#Include current_url.ahk
Menu, Tray, Icon, % A_WinDir "\system32\netshell.dll" , 86 ; Shows a world icon in the system tray

ModernBrowsers := "ApplicationFrameWindow,Chrome_WidgetWin_0,Chrome_WidgetWin_1,Maxthon3Cls_MainFrm,MozillaWindowClass,Slimjet_WidgetWin_1"
LegacyBrowsers := "IEFrame,OperaWindowClass"

;^+!u:: 
;	nTime := A_TickCount
;	sURL := GetActiveBrowserURL()
;	WinGetClass, sClass, A
;	If (sURL != "")
;		MsgBox, % "The URL is  sURL`nEllapsed time: " (A_TickCount - nTime) " ms (" sClass ")"
;	Else If sClass In % ModernBrowsers "," LegacyBrowsers
;		MsgBox, % "The URL couldn't be determined (" sClass ")"
;	Else
;		MsgBox, % "Not a browser or browser not supported (" sClass ")"
;Return


#c::
clipboard := GetActiveBrowserURL()
Return"""
    }


    override fun getAdditionalHighlightingTagToDescriptorMap() = ANNOTATOR_TAGS

    override fun getAttributeDescriptors() = ATTRS

    override fun getColorDescriptors(): Array<ColorDescriptor> = ColorDescriptor.EMPTY_ARRAY

    override fun getDisplayName() = "AutoHotKey"
}
