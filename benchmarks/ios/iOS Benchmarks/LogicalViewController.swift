//
//  LogicalViewController.swift
//  iOS Benchmarks
//
//  Created by Alexey Buzdin on 5/21/15.
//  Copyright (c) 2015 Alexey Buzdin. All rights reserved.
//

import UIKit
import CoreFoundation

class LogicalViewController: UIViewController {

    override func viewDidLoad() {
        super.viewDidLoad()
        // Do any additional setup after loading the view, typically from a nib.
    }

    override func didReceiveMemoryWarning() {
        super.didReceiveMemoryWarning()
        // Dispose of any resources that can be recreated.
    }

    @IBOutlet weak var runBtn: UIButton!    
    @IBOutlet weak var benchmarkResult: UILabel!
    
    @IBAction func run(sender: AnyObject) {
        self.runBtn.enabled = false;
        let qualityOfServiceClass = QOS_CLASS_BACKGROUND
        let backgroundQueue = dispatch_get_global_queue(qualityOfServiceClass, 0)
        dispatch_async(backgroundQueue, {
            var array = [Float]();

            for (var times:Int = 0; times < 200; times++) {
            
                let now = CACurrentMediaTime();
            
                let sum = self.testableMethod();

                let end = CACurrentMediaTime();
            
                array.append(Float(end - now)*10);
            }
            var str = "";
            for obj in array {
                str += obj.description + ", ";
            }
            NSLog("array: " + str);
            dispatch_async(dispatch_get_main_queue(), { () -> Void in
                self.benchmarkResult.text = self.getAverage(array).description;
                self.runBtn.enabled = true;
            })
        });
    }

    func testableMethod() -> CLongLong {
        var sum: CLongLong = 0;
        for (var x = 1; x <= 100000; x++) {
            sum += x;
        }
        return sum;
    }

    func getAverage(trials: [Float]) -> Float {
        var sum: Float = 0.0;
        for trial in trials {
            sum = sum + trial;
        }
        return sum / Float(trials.count);
    }
}

