//
//  UndefinedAppIosApp.swift
//  UndefinedAppIos
//
//  Created by Sergey Shisheya on 28.06.2023.
//

import SwiftUI
import shared

@main
struct UndefinedAppIosApp: App {
    
    init() {
        HelperKt.doInitKoin()
    }
    
    var body: some Scene {
        WindowGroup {
            ContentView(viewModel: ContentView.ViewModel())
        }
    }
}
