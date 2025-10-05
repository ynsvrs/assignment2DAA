import pandas as pd
import matplotlib.pyplot as plt

data = pd.read_csv('docs/performance-plots/benchmark_all.csv')
for input_type in data['type'].unique():
    subset = data[data['type'] == input_type]
    plt.plot(subset['size'], subset['time_ms'], marker='o', label=input_type)

plt.xscale('log')
plt.xlabel('Input Size (n)')
plt.ylabel('Time (ms)')
plt.title('Max-Heap Performance')
plt.legend()
plt.savefig('docs/performance-plots/time_vs_size.png')
plt.show()