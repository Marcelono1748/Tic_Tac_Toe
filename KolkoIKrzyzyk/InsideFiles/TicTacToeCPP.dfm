object Form1: TForm1
  Left = 528
  Top = 138
  Width = 393
  Height = 632
  AutoSize = True
  Caption = 'TicTacToe'
  Color = clBtnFace
  Font.Charset = DEFAULT_CHARSET
  Font.Color = clWindowText
  Font.Height = -11
  Font.Name = 'MS Sans Serif'
  Font.Style = []
  OldCreateOrder = False
  OnCreate = FormCreate
  PixelsPerInch = 96
  TextHeight = 13
  object Back: TImage
    Left = 0
    Top = 0
    Width = 377
    Height = 593
    Cursor = crArrow
    Enabled = False
  end
  object TL: TImage
    Left = 24
    Top = 16
    Width = 105
    Height = 105
    Cursor = crHandPoint
    OnClick = TLClick
  end
  object Turn: TImage
    Left = 80
    Top = 376
    Width = 217
    Height = 65
  end
  object TM: TImage
    Left = 136
    Top = 16
    Width = 105
    Height = 105
    Cursor = crHandPoint
    OnClick = TMClick
  end
  object TR: TImage
    Left = 248
    Top = 16
    Width = 105
    Height = 105
    Cursor = crHandPoint
    OnClick = TRClick
  end
  object ML: TImage
    Left = 24
    Top = 128
    Width = 105
    Height = 105
    Cursor = crHandPoint
    OnClick = MLClick
  end
  object MM: TImage
    Left = 136
    Top = 128
    Width = 105
    Height = 105
    Cursor = crHandPoint
    OnClick = MMClick
  end
  object MR: TImage
    Left = 248
    Top = 128
    Width = 105
    Height = 105
    Cursor = crHandPoint
    OnClick = MRClick
  end
  object BL: TImage
    Left = 24
    Top = 240
    Width = 105
    Height = 105
    Cursor = crHandPoint
    OnClick = BLClick
  end
  object BM: TImage
    Left = 136
    Top = 240
    Width = 105
    Height = 105
    Cursor = crHandPoint
    OnClick = BMClick
  end
  object BR: TImage
    Left = 248
    Top = 240
    Width = 105
    Height = 105
    Cursor = crHandPoint
    OnClick = BRClick
  end
  object Button1: TImage
    Left = 128
    Top = 472
    Width = 137
    Height = 65
    Cursor = crHandPoint
    OnClick = Button1Click
  end
end
