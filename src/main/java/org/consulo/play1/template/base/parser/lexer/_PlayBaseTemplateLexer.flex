package org.napile.playJava4idea.template.base.parser.lexer;

import com.intellij.lexer.FlexLexer;
import com.intellij.psi.tree.IElementType;
import com.intellij.util.containers.Stack;
import org.napile.playJava4idea.template.base.parser.PlayBaseTemplateTokens;

%%

%public
%class _PlayBaseTemplateLexer
%implements FlexLexer
%final
%unicode
%function advance
%type IElementType
%eof{ return;
%eof}

WHITE_SPACE = \ |\n|\t|\f|\r

%%
{WHITE_SPACE} {return PlayBaseTemplateTokens.WHITE_SPACE;}

"{" {return PlayBaseTemplateTokens.LBRACE;}
"}" {return PlayBaseTemplateTokens.RBRACE;}
":" {return PlayBaseTemplateTokens.COLON;}
"," {return PlayBaseTemplateTokens.COMMA;}
"/" {return PlayBaseTemplateTokens.DIV;}

"#" {return PlayBaseTemplateTokens.SHARP;}
"*" {return PlayBaseTemplateTokens.ASTERISK;}
"&" {return PlayBaseTemplateTokens.AND;}
"%" {return PlayBaseTemplateTokens.PERC;}
"$" {return PlayBaseTemplateTokens.DOLLAR;}
"@@" {return PlayBaseTemplateTokens.ATAT;}
"@" {return PlayBaseTemplateTokens.AT;}

'([^'\\]|\\.)*' { return PlayBaseTemplateTokens.STRING; }

. {return PlayBaseTemplateTokens.TEMPLATE_TEXT;}