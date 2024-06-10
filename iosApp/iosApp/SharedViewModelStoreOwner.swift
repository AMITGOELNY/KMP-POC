//
//  SharedViewModelStoreOwner.swift
//  iosApp
//
//  Created by Amit Goel on 6/9/24.
//  Copyright © 2024 orgName. All rights reserved.
//

import Foundation
import Shared

class SharedViewModelStoreOwner<VM : ViewModel> : ObservableObject, ViewModelStoreOwner {
    var viewModelStore: ViewModelStore = ViewModelStore()
    
    private let key: String = String(describing: type(of: VM.self))
    
    init(_ viewModel: VM = .init()) {
        viewModelStore.put(key: key, viewModel: viewModel)
    }
    
    var instance: VM {
        get {
            return viewModelStore.get(key: key) as! VM
        }
    }
    
    deinit {
        viewModelStore.clear()
    }
}

